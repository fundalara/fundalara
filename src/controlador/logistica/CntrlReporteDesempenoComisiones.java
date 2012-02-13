package controlador.logistica;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Actividad;
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
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Datebox;

import servicio.interfaz.IServicioActividad;

import comun.ConeccionBD;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para el filtro dinámico de actividades
 * 
 * @author Reinaldo L.
 * 
 * */
public class CntrlReporteDesempenoComisiones extends GenericForwardComposer {

	private AnnotateDataBinder binder;
	private Actividad actividad;
	private List<Actividad> listaActividades;
	private IServicioActividad servicioActividad;

	// private DatoBasico tipoActividad;
	// private DatoBasico complementaria;
	// private DatoBasico mantenimiento;
	// private List<DatoBasico> listaTipoActividad = new
	// ArrayList<DatoBasico>();
	// private IServicioDatoBasico servicioDatoBasico;

	// pantalla
	private Datebox fechaInicio;
	private Datebox fechaFin;

	private Connection con;
	private Map<String, Object> parameters = new HashMap<String, Object>();

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);
		listaActividades = new ArrayList<Actividad>();
	}

	public void onClick$btnBuscar() {
		fechaInicio.getValue();
		fechaFin.getValue();
		listaActividades = servicioActividad.listarComplementarias(fechaInicio.getValue(), fechaFin.getValue());
		binder.loadAll();
	}

	public void onClick$btnGenerar() throws JRException, SQLException, WrongValueException, ParseException {

		con = ConeccionBD.getCon("postgres", "postgres", "admin");

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = new Date();
		parameters.put("FECHA", formato.format(fecha));
		formato.applyPattern("hh:mm");
		parameters.put("HORA", formato.format(fecha));
		parameters.put("CodigoActividad", actividad.getCodigoActividad());

		String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/Reportes/Logistica/ReporteDesempenoComisiones.jrxml");
		JasperReport report = JasperCompileManager.compileReport(rutaReporte);
		JasperPrint print = JasperFillManager.fillReport(report, parameters, con);

		// Exporta el informe a PDF
		String rutaExportar = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/Reportes/Logistica/ReporteDesempenoComisiones.pdf");
		JRExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(rutaExportar));

		exporter.exportReport();

		// Para visualizar el pdf con el visor de Jasper
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

	// public DatoBasico getTipoActividad() {
	// return tipoActividad;
	// }
	//
	// public void setTipoActividad(DatoBasico tipoActividad) {
	// this.tipoActividad = tipoActividad;
	// }
	//
	// public DatoBasico getComplementaria() {
	// return complementaria;
	// }
	//
	// public void setComplementaria(DatoBasico complementaria) {
	// this.complementaria = complementaria;
	// }
	//
	// public DatoBasico getMantenimiento() {
	// return mantenimiento;
	// }
	//
	// public void setMantenimiento(DatoBasico mantenimiento) {
	// this.mantenimiento = mantenimiento;
	// }
	//
	// public List<DatoBasico> getListaTipoActividad() {
	// return listaTipoActividad;
	// }
	//
	// public void setListaTipoActividad(List<DatoBasico> listaTipoActividad) {
	// this.listaTipoActividad = listaTipoActividad;
	// }

}
