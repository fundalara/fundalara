package controlador.logistica;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Actividad;
import modelo.DatoBasico;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioDatoBasico;
import servicio.interfaz.IServicioActividad;

import comun.ConeccionBD;

public class CntrlFrmReporteProgresoActividad extends GenericForwardComposer {

	private AnnotateDataBinder binder;
	private Actividad actividad;
	private Connection con;
	private List<Actividad> listaActividades = new ArrayList<Actividad>();
	private IServicioActividad servicioActividad;
	private Map<String, Object> parameters = new HashMap<String, Object>();

	private String string;
	private DatoBasico tipoActividad;
	private DatoBasico complementaria;
	private DatoBasico mantenimiento;
	private List<DatoBasico> listaTipoActividad = new ArrayList<DatoBasico>();
	private List<String> listaString = new ArrayList<String>();
	private ServicioDatoBasico servicioDatoBasico;;

	private Window frmReporte;
	private Combobox cmbTipoActividad;
	private Datebox fechaInicio;
	private Datebox fechaFin;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);
		frmReporte = (Window) comp;
		complementaria = (DatoBasico) servicioDatoBasico.buscarPorCodigo(307);
		mantenimiento = (DatoBasico) servicioDatoBasico.buscarPorCodigo(500);
		listaString.add(complementaria.getNombre());
		listaString.add(mantenimiento.getNombre());

		System.out.println("do after again");

	}

	public void onSelect$cmbTipoActividad() {
		listaActividades.clear();
	}

	public void onChange$fechaFin() {

		fechaInicio.getValue();
		fechaFin.getValue();
		listaActividades.clear();

		if (string.compareTo("ACTIVIDADES COMPLEMENTARIAS") == 0) {
			listaActividades = servicioActividad.listarComplementarias(fechaInicio.getValue(), fechaFin.getValue());
		} else {
			listaActividades = servicioActividad.listarMantenimientos(fechaInicio.getValue(), fechaFin.getValue());
		}
		binder.loadAll();
	}

	public void onClick$btnExportar() throws JRException, SQLException {

		con = ConeccionBD.getCon("postgres", "postgres", "admin");

		// String rutaSubReporte =
		// Sessions.getCurrent().getWebApp().getRealPath("WEB-INF/Reportes/Logistica/RepoprteProgresoActividad2.jrxml");
		// JasperReport subReport =
		// JasperCompileManager.compileReport(rutaSubReporte);
		// parameters.put("CodigoActividad", actividad.getCodigoActividad());
		// JasperPrint printSub = JasperFillManager.fillReport(subReport,
		// parameters , con);

		String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("WEB-INF/Reportes/Logistica/ReporteProgresoActividad.jrxml");
		JasperReport report = JasperCompileManager.compileReport(rutaReporte);
		parameters.put("CodigoActividad", actividad.getCodigoActividad());
		JasperPrint print = JasperFillManager.fillReport(report, parameters, con);

		JRExporter exporter = new JRPdfExporter();

		String rutaExportar = Sessions.getCurrent().getWebApp().getRealPath("WEB-INF/Reportes/Logistica/ReporteProgresoActividad.pdf");

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(rutaExportar));

		exporter.exportReport();

		// Para visualizar el reporte con el visor de JasperReport
		JasperViewer.viewReport(print, false);
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

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
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

	public List<String> getListaString() {
		return listaString;
	}

	public void setListaString(List<String> listaString) {
		this.listaString = listaString;
	}

}