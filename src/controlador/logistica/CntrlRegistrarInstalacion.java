package controlador.logistica;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.DatoBasico;
import modelo.Instalacion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;

import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioInstalacion;

import comun.TipoDatoBasico;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para el registro de nuevas instalaciones
 * 
 * @author Reinaldo L.
 * 
 * */
public class CntrlRegistrarInstalacion extends GenericForwardComposer {

	private Instalacion instalacion = new Instalacion();

	private IServicioDatoBasico servicioDatoBasico;
	private IServicioInstalacion servicioInstalacion;

	private List<Instalacion> instalaciones;
	private List<DatoBasico> tiposInstalaciones;

	private Textbox txtDescripcion;
	private Combobox cmbTipoInstalacion;
	private Spinner spCapacidad;
	private Doublebox dboxTamano;

	private AnnotateDataBinder binder;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);
	}

	public Instalacion getInstalacion() {
		return instalacion;
	}

	public void setInstalacion(Instalacion instalacion) {
		this.instalacion = instalacion;
	}

	public List<Instalacion> getInstalaciones() {
		instalaciones = servicioInstalacion.listarActivos();
		return instalaciones;
	}

	public void setInstalaciones(List<Instalacion> instalaciones) {
		this.instalaciones = instalaciones;
	}

	public List<DatoBasico> getTiposInstalaciones() {
		tiposInstalaciones = servicioDatoBasico.buscar(TipoDatoBasico.TIPOS_INSTALACIONES);
		return tiposInstalaciones;
	}

	public void setTiposInstalaciones(List<DatoBasico> tiposInstalaciones) {
		this.tiposInstalaciones = tiposInstalaciones;
	}

	public void onClick$btnRegistrar() throws InterruptedException {

		validar();
		instalacion.setCodigoInstalacion(servicioInstalacion.listar().size() + 1);
		instalacion.setEstatus('A');
		servicioInstalacion.agregar(instalacion);

		this.onClick$btnCancelar();
		Messagebox.show("Datos agregados exitosamente", "Mensaje", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void onClick$btnCancelar() {
		instalacion = new Instalacion();
		binder.loadAll();
	}

	public void onClick$btnModificar() throws InterruptedException {
		servicioInstalacion.actualizar(instalacion);
		onClick$btnCancelar();
		Messagebox.show("Datos modificados exitosamente", "Mensaje", Messagebox.OK, Messagebox.EXCLAMATION);
	}

	public void onClick$btnEliminar() throws InterruptedException {

		Messagebox.show("¿Realmente desea eliminar esta instalación?", "Importante", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
				new EventListener() {
					@Override
					public void onEvent(Event arg0) throws InterruptedException {
						if (arg0.getName().toString() == "onOK") {
							instalacion.setEstatus('E');
							servicioInstalacion.actualizar(instalacion);
							onClick$btnCancelar();
							Messagebox.show("Datos eliminados exitosamente", "Mensaje", Messagebox.OK, Messagebox.EXCLAMATION);
						}
					}
				});
	}

	public void validar() {
		txtDescripcion.getValue();
		cmbTipoInstalacion.getValue();
		spCapacidad.getValue();
		dboxTamano.getValue();
	}

	public void onClick$btnImprimir() throws JRException {

		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(instalaciones);

		Map parameters = new HashMap();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = new Date();
		parameters.put("FECHA", formato.format(fecha));
		formato.applyPattern("hh:mm");
		parameters.put("HORA", formato.format(fecha));

		String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/Reportes/Logistica/ReporteListadoInstalaciones.jrxml");
		JasperReport report = JasperCompileManager.compileReport(rutaReporte);
		JasperPrint print = JasperFillManager.fillReport(report, parameters, ds);

		// Exporta el informe a PDF
		String rutaExportar = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/Reportes/Logistica/ReporteListadoInstalaciones.pdf");
		JRExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(rutaExportar));

		exporter.exportReport();

		// Para visualizar el pdf con el visor de Jasper
		JasperViewer.viewReport(print, false);
	}

}
