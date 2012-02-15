package controlador.logistica;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Almacen;
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
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioInstalacion;
import servicio.interfaz.IServicioAlmacen;

import comun.MensajeMostrar;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para el registro de nuevos almacenes
 * 
 * @author Reinaldo L.
 * 
 * */
public class CntrlRegistrarAlmacen extends GenericForwardComposer {

	private Almacen almacen = new Almacen();

	private ServicioInstalacion servicioInstalacion;
	private IServicioAlmacen servicioAlmacen;

	private List<Almacen> almacenes;
	private List<Instalacion> instalaciones;

	private Textbox txtNombre;
	private Combobox cmbInstalacion;
	private Textbox txtDescripcion;
	private Doublebox dboxCapacidad;

	private AnnotateDataBinder binder;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);
		almacenes = servicioAlmacen.listarActivos();
		instalaciones = servicioInstalacion.listarActivos();
	}

	public Almacen getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public List<Almacen> getAlmacenes() {
		return almacenes;
	}

	public void setAlmacenes(List<Almacen> almacenes) {
		this.almacenes = almacenes;
	}

	public List<Instalacion> getInstalaciones() {
		return instalaciones;
	}

	public void setInstalaciones(List<Instalacion> instalaciones) {
		this.instalaciones = instalaciones;
	}

	public void onClick$btnRegistrar() throws InterruptedException {

		validar();
		almacen.setCodigoAlmacen(servicioAlmacen.listar().size() + 1);
		almacen.setEstatus('A');
		servicioAlmacen.agregar(almacen);
		almacenes.add(almacen);
		Messagebox.show(MensajeMostrar.REGISTRO_EXITOSO, MensajeMostrar.TITULO + "Información", Messagebox.OK, Messagebox.INFORMATION);
		this.onClick$btnCancelar();

	}

	public void onClick$btnCancelar() {
		almacen = new Almacen();
		binder.loadComponent(txtNombre);
		binder.loadComponent(txtDescripcion);
		binder.loadComponent(dboxCapacidad);
		cmbInstalacion.setValue("-Seleccione-");
	}

	public void onClick$btnModificar() throws InterruptedException {
		servicioAlmacen.actualizar(almacen);
		Messagebox.show(MensajeMostrar.MODIFICACION_EXITOSA, MensajeMostrar.TITULO + "Información", Messagebox.OK, Messagebox.INFORMATION);
		this.onClick$btnCancelar();
	}

	public void onClick$btnEliminar() throws InterruptedException {

		Messagebox.show("¿Realmente desea eliminar este almacén?", MensajeMostrar.TITULO + "Importante", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener() {
					@Override
					public void onEvent(Event arg0) throws InterruptedException {
						if (arg0.getName().toString() == "onOK") {
							almacen.setEstatus('E');
							servicioAlmacen.actualizar(almacen);
							almacenes.remove(almacen);
							Messagebox.show(MensajeMostrar.ELIMINACION_EXITOSA, MensajeMostrar.TITULO + "Información", Messagebox.OK,
									Messagebox.INFORMATION);
							onClick$btnCancelar();
						}
					}
				});
	}

	public void validar() {
		txtNombre.getValue();
		cmbInstalacion.getValue();
		txtDescripcion.getValue();
		dboxCapacidad.getValue();
	}

	public void onClick$btnImprimir() throws JRException {

		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(almacenes);

		Map parameters = new HashMap();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = new Date();
		parameters.put("FECHA", formato.format(fecha));
		formato.applyPattern("hh:mm");
		parameters.put("HORA", formato.format(fecha));

		String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/Reportes/Logistica/ReporteListadoAlmacenes.jrxml");
		JasperReport report = JasperCompileManager.compileReport(rutaReporte);
		JasperPrint print = JasperFillManager.fillReport(report, parameters, ds);

		// Exporta el informe a PDF
		String rutaExportar = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/Reportes/Logistica/ReporteListadoAlmacenes.pdf");
		JRExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(rutaExportar));

		exporter.exportReport();

		// Para visualizar el pdf con el visor de Jasper
		JasperViewer.viewReport(print, false);

	}

}
