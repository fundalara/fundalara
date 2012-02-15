package controlador.logistica;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.Actividad;
import modelo.DatoBasico;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Window;

import servicio.interfaz.IServicioActividad;
import servicio.interfaz.IServicioDatoBasico;

import comun.TipoDatoBasico;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para el filtro dinámico de actividades
 * 
 * @author Reinaldo L.
 * @author Eduardo Q.
 * 
 * */
public class CntrlFrmReporteDinamicoActividad extends GenericForwardComposer {

	private AnnotateDataBinder binder;
	private Actividad actividad;
	private List<Actividad> listaActividades;
	private IServicioActividad servicioActividad;

	private DatoBasico tipoActividad;
	private DatoBasico complementaria;
	private DatoBasico mantenimiento;
	private List<DatoBasico> listaTipoActividad = new ArrayList<DatoBasico>();
	private IServicioDatoBasico servicioDatoBasico;

	// pantalla
	private Window frmReporte;
	private Datebox fechaInicio;
	private Datebox fechaFin;
	private Combobox cmbListaTipoActividad;
	private Checkbox checkComisionesAct, checkTareasAct, checkMaterialesAct, checkTareasMant, checkMaterialesMant;
	private Panel panelComisiones, panelTareas, panelMateriales;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);
		frmReporte = (Window) comp;
		complementaria = (DatoBasico) servicioDatoBasico.buscarPorCodigo(TipoDatoBasico.ACTIVIDADES_COMPLEMENTARIAS.getCodigo());
		mantenimiento = (DatoBasico) servicioDatoBasico.buscarPorCodigo(TipoDatoBasico.ACTIVIDADES_MANTENIMIENTO.getCodigo());
		listaTipoActividad.add(complementaria);
		listaTipoActividad.add(mantenimiento);
		listaActividades = new ArrayList<Actividad>();
	}

	/**
	 * Carga una lista de actividades complementarias o de mantenimientos y
	 * muestra las opciones seleccionadas por el usuario
	 * 
	 */
	public void onClick$btnBuscar() {
		cmbListaTipoActividad.getValue();
		fechaInicio.getValue();
		fechaFin.getValue();
		if (tipoActividad.getCodigoDatoBasico() == TipoDatoBasico.ACTIVIDADES_MANTENIMIENTO.getCodigo()) {
			listaActividades = servicioActividad.listarMantenimientos(fechaInicio.getValue(), fechaFin.getValue());
			panelMateriales.setVisible(checkMaterialesMant.isChecked());
			panelTareas.setVisible(checkTareasMant.isChecked());
		} else {
			listaActividades = servicioActividad.listarComplementarias(fechaInicio.getValue(), fechaFin.getValue());
			panelComisiones.setVisible(checkComisionesAct.isChecked());
			panelMateriales.setVisible(checkMaterialesAct.isChecked());
			panelTareas.setVisible(checkTareasAct.isChecked());
		}
		binder.loadAll();
	}

	/**
	 * Habilita las distintas opciones por las que puede filtrar el usuario
	 * dependiendo de la selección del tipo de actividad
	 * 
	 */
	public void onSelect$cmbListaTipoActividad() {
		if (tipoActividad.getCodigoDatoBasico() == TipoDatoBasico.ACTIVIDADES_MANTENIMIENTO.getCodigo()) {
			((Panel) frmReporte.getFellow("panelMantenimiento")).setVisible(true);
			((Panel) frmReporte.getFellow("panelComplementaria")).setVisible(false);
		} else {
			((Panel) frmReporte.getFellow("panelComplementaria")).setVisible(true);
			((Panel) frmReporte.getFellow("panelMantenimiento")).setVisible(false);
		}
		panelComisiones.setVisible(false);
		panelMateriales.setVisible(false);
		panelTareas.setVisible(false);
		binder.loadAll();
	}

	/**
	 * Exporta una lista de actividades complementarias o de mantenimientos en
	 * un archivo txt listando sus comisiones, tareas y materiales
	 * 
	 * @throws IOException
	 * 
	 */
	public void onClick$btnExportar() throws JRException, IOException {

		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listaActividades);

		String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("WEB-INF/reportes/reporteDinamico.jrxml");
		JasperReport report = JasperCompileManager.compileReport(rutaReporte);

		JasperPrint print = JasperFillManager.fillReport(report, null, ds);

		JRExporter exporter = new JRTextExporter();

		String rutaExportar = Sessions.getCurrent().getWebApp().getRealPath("WEB-INF/reportes/reporteDinamico.txt");

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(rutaExportar));

		exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, 12f);// text
		// exporter
		exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, 12f);// text
		// exporter

		exporter.exportReport();

		File archivo = new File(rutaExportar);
		FileInputStream fis = new FileInputStream(archivo);

		byte fileContent[] = new byte[(int) archivo.length()];

		fis.read(fileContent);

		String strFileContent = new String(fileContent);

		Filedownload.save(strFileContent.getBytes(), "text/plain", "actividades.txt");

	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public List<Actividad> getListaActividades() {
		return listaActividades;
	}

	public void setListaActividades(List<Actividad> listaActividades) {
		this.listaActividades = listaActividades;
	}

	public DatoBasico getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(DatoBasico tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public DatoBasico getComplementaria() {
		return complementaria;
	}

	public void setComplementaria(DatoBasico complementaria) {
		this.complementaria = complementaria;
	}

	public DatoBasico getMantenimiento() {
		return mantenimiento;
	}

	public void setMantenimiento(DatoBasico mantenimiento) {
		this.mantenimiento = mantenimiento;
	}

	public List<DatoBasico> getListaTipoActividad() {
		return listaTipoActividad;
	}

	public void setListaTipoActividad(List<DatoBasico> listaTipoActividad) {
		this.listaTipoActividad = listaTipoActividad;
	}

}
