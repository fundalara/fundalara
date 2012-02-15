package controlador.logistica;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Almacen;
import modelo.DatoBasico;
import modelo.Material;
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
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;

import servicio.interfaz.IServicioAlmacen;
import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioMaterial;

import comun.MensajeMostrar;
import comun.TipoDatoBasico;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para el registro de nuevos materiales
 * 
 * @author Reinaldo L.
 * 
 * */
public class CntrlRegistrarMaterial extends GenericForwardComposer {

	private Material material = new Material();
	private DatoBasico tipoMaterial = new DatoBasico();

	private IServicioDatoBasico servicioDatoBasico;
	private IServicioMaterial servicioMaterial;
	private IServicioAlmacen servicioAlmacen;

	private List<DatoBasico> tiposMateriales;
	private List<DatoBasico> clasificacionesMateriales;
	private List<DatoBasico> unidadesMedida;
	private List<Material> materiales;
	private List<Almacen> almacenes;

	private Combobox cmbTipos;
	private Combobox cmbClasificaciones;
	private Combobox cmbAlmacen;
	private Textbox txtDescripcion;
	private Spinner spExistencia;
	private Button btnEliminar;
	private Button btnModificar;
	private Button btnRegistrar;

	private AnnotateDataBinder binder;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);
		clasificacionesMateriales = servicioDatoBasico.buscar(TipoDatoBasico.CLASIFICACIONES_MATERIALES);
	}

	public List<DatoBasico> getClasificacionesMateriales() {
		return clasificacionesMateriales;
	}

	public void setClasificacionesMateriales(List<DatoBasico> clasificacionesMateriales) {
		this.clasificacionesMateriales = clasificacionesMateriales;
	}

	public DatoBasico getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(DatoBasico tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	public List<DatoBasico> getTiposMateriales() {
		tiposMateriales = servicioDatoBasico.buscar(TipoDatoBasico.TIPOS_MATERIALES);
		return tiposMateriales;
	}

	public void setTiposMateriales(List<DatoBasico> tiposMateriales) {
		this.tiposMateriales = tiposMateriales;
	}

	public List<DatoBasico> getUnidadesMedida() {
		unidadesMedida = servicioDatoBasico.buscar(TipoDatoBasico.UNIDADES_MEDIDA);
		return unidadesMedida;
	}

	public void setUnidadesMedida(List<DatoBasico> unidadesMedida) {
		this.unidadesMedida = unidadesMedida;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public List<Material> getMateriales() {
		materiales = servicioMaterial.listarActivos();
		return materiales;
	}

	public void setMateriales(List<Material> materiales) {
		this.materiales = materiales;
	}

	public List<Almacen> getAlmacenes() {
		almacenes = servicioAlmacen.listarActivos();
		return almacenes;
	}

	public void setAlmacenes(List<Almacen> almacenes) {
		this.almacenes = almacenes;
	}

	public void onSelect$cmbTipos() {
		clasificacionesMateriales = servicioDatoBasico.buscarDatosPorRelacion(tipoMaterial);
	}

	public void onClick$btnRegistrar() throws InterruptedException {

		validar();

		material.setCodigoMaterial(servicioMaterial.listar().size() + 1);
		material.setDescripcion(txtDescripcion.getValue().toUpperCase());
		material.setCantidadDisponible(material.getCantidadExistencia());
		material.setEstatus('A');

		servicioMaterial.agregar(material);

		this.onClick$btnCancelar();
		Messagebox.show(MensajeMostrar.REGISTRO_EXITOSO, MensajeMostrar.TITULO + "Información", Messagebox.OK, Messagebox.INFORMATION);

	}

	public void onClick$btnCancelar() {
		material = new Material();
		tipoMaterial = new DatoBasico();
		spExistencia.setDisabled(false);
		btnEliminar.setDisabled(true);
		btnModificar.setDisabled(true);
		btnRegistrar.setDisabled(false);
		binder.loadAll();
	}

	public void onSelect$lboxMateriales() throws InterruptedException {
		tipoMaterial = material.getDatoBasicoByCodigoTipoMaterial().getDatoBasico();
		spExistencia.setDisabled(true);
		binder.loadAll();
	}

	public void onClick$btnModificar() throws InterruptedException {
		material.setDescripcion(txtDescripcion.getValue().toUpperCase());
		servicioMaterial.actualizar(material);
		this.onClick$btnCancelar();
		Messagebox.show(MensajeMostrar.MODIFICACION_EXITOSA, MensajeMostrar.TITULO + "Información", Messagebox.OK, Messagebox.INFORMATION);
	}

	public void onClick$btnEliminar() throws InterruptedException {

		Messagebox.show("¿Desea eliminar este material?", MensajeMostrar.TITULO + "Importante", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener() {
					@Override
					public void onEvent(Event arg0) throws InterruptedException {
						if (arg0.getName().toString() == "onOK") {

							material.setEstatus('E');
							servicioMaterial.actualizar(material);
							Messagebox.show(MensajeMostrar.ELIMINACION_EXITOSA, MensajeMostrar.TITULO + "Información", Messagebox.OK,
									Messagebox.INFORMATION);
							onClick$btnCancelar();
						}
					}
				});
	}

	public void validar() {
		cmbTipos.getValue();
		cmbClasificaciones.getValue();
		txtDescripcion.getValue();
		spExistencia.getValue();
		cmbAlmacen.getValue();
	}

	public void onClick$btnImprimir() throws JRException {

		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(materiales);

		Map<String, Object> parameters = new HashMap<String, Object>();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = new Date();
		parameters.put("FECHA", formato.format(fecha));
		formato.applyPattern("hh:mm");
		parameters.put("HORA", formato.format(fecha));

		String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/Reportes/Logistica/ReporteListadoMateriales.jrxml");
		JasperReport report = JasperCompileManager.compileReport(rutaReporte);
		JasperPrint print = JasperFillManager.fillReport(report, parameters, ds);

		// Exporta el informe a PDF
		String rutaExportar = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/Reportes/Logistica/ReporteListadoMateriales.pdf");
		JRExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(rutaExportar));

		exporter.exportReport();

		// Para visualizar el pdf con el visor de Jasper
		JasperViewer.viewReport(print, false);
	}
}
