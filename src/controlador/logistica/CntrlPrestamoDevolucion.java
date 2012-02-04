package controlador.logistica;

import java.util.Date;
import java.util.List;

import modelo.Actividad;
import modelo.ActividadPlanificada;
import modelo.DatoBasico;
import modelo.DetalleRequisicion;
import modelo.Material;
import modelo.MaterialActividad;
import modelo.MaterialActividadPlanificada;
import modelo.PlanificacionActividad;
import modelo.ProveedorBanco;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import servicio.interfaz.IServicioActividad;
import servicio.interfaz.IServicioActividadPlanificada;
import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioMaterial;
import servicio.interfaz.IServicioMaterialActividad;
import servicio.interfaz.IServicioMaterialActividadPlanificada;
import servicio.interfaz.IServicioPlanificacionActividad;

import comun.TipoDatoBasico;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para el control de prestamo y devolucion de materiales
 * 
 * @author Eduardo L.
 * 
 * */
public class CntrlPrestamoDevolucion extends GenericForwardComposer {

	private PlanificacionActividad planificacionActividad;
	private Actividad actividad;
	private Actividad actividadD;
	private MaterialActividad materialActividad;
	private MaterialActividad materialActividadD;
	private List<MaterialActividadPlanificada> solicitudes;

	private MaterialActividadPlanificada solicitud = new MaterialActividadPlanificada();
	private List<MaterialActividad> materialesActividadesPorDevolver;
	private IServicioMaterialActividad servicioMaterialActividad;
	private IServicioActividad servicioActividad;
	private MaterialActividadPlanificada materialActividadPlanificada = new MaterialActividadPlanificada();
	private IServicioMaterialActividadPlanificada servicioMaterialActividadPlanificada;
	private AnnotateDataBinder binder;
	private Integer cantidadNecesitada;
	private Integer cantidadDisponible;
	private Integer cantidadFaltante;
	private Integer cantidadDevolver;
	private Integer cantidadMalEstado;
	private DetalleRequisicion detalleRequision;

	// Componentes
	Component frmPrestamoDevolucion;
	Textbox txtActividadDevolucion;
	Window frmPrestamoDevolucionw;
	Window frmCatalogoPlanificacionActividad;
	// requisicion
	Spinner txtFaltante;
	Label labelRequisicion;
	Button btnRequisicion;
	Component formRequisicion;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);
		// solicitudes = servicioMaterialActividadPlanificada.listar();
		// materialesActividadesPorDevolver = servicioMaterialActividad
		// .listarPorDevolver();
	}

	public void onSelect$lboxSolicitudMaterial() {
		cantidadDisponible = solicitud.getMaterial().getCantidadDisponible();
		cantidadNecesitada = solicitud.getCantidadRequerida();
		cantidadFaltante = 0;

		if (cantidadDisponible >= cantidadNecesitada) {
			cantidadFaltante = 0;
			cantidadNecesitada = solicitud.getCantidadRequerida();
		} else {
			cantidadFaltante = cantidadNecesitada - cantidadDisponible;
			cantidadNecesitada = cantidadDisponible;
		}

		if (cantidadFaltante > 0) {
			btnRequisicion.setVisible(true);
			txtFaltante.setVisible(true);
			labelRequisicion.setVisible(true);

		}
		binder.loadAll();
	}

	public void onSelect$lboxDevolucionMaterial() {
		materialActividadD.setCantidadDevuelta(materialActividadD
				.getCantidadEntregada());
		binder.loadAll();
	}

	public void onClick$btnGuardarSolicitud() throws InterruptedException {
		materialActividad = new MaterialActividad();
		Actividad actividad = new Actividad();

		actividad = servicioActividad.buscarActividad(solicitud
				.getPlanificacionActividad());

		materialActividad.setActividad(actividad);
		materialActividad.setMaterial(solicitud.getMaterial());
		materialActividad.setEstatus('A');
		materialActividad.setCantidadEntregada(cantidadNecesitada);
		materialActividad.getMaterial().setCantidadDisponible(
				cantidadDisponible - cantidadNecesitada);
		materialActividad.setCodigoMaterialActividad(servicioMaterialActividad
				.listar().size());
		servicioMaterialActividad.agregar(materialActividad);
		solicitud.setEstatus('E');
		servicioMaterialActividadPlanificada.agregar(solicitud);
		this.onClick$btnCancelarSolicitud();
		this.onClick$menuTodasP();
		this.onClick$menuTodasD();
		Messagebox.show("Prestamo realizado exitosamente", "Mensaje",
				Messagebox.OK, Messagebox.EXCLAMATION);

	}

	public void onClick$btnCancelarSolicitud() {
		materialActividad = new MaterialActividad();
		materialActividadD = new MaterialActividad();
		solicitud = new MaterialActividadPlanificada();
		materialActividadPlanificada = new MaterialActividadPlanificada();
		cantidadDisponible = 0;
		cantidadFaltante = 0;
		cantidadNecesitada = 0;
		cantidadDevolver = 0;
		cantidadMalEstado = 0;
		binder.loadAll();
		this.onClick$menuTodasP();
		this.onClick$menuTodasD();
		btnRequisicion.setVisible(true);
		txtFaltante.setVisible(false);
		labelRequisicion.setVisible(false);
	}

	public void onClick$btnGuardarDevolucion() throws InterruptedException {
		Date fechaDevolucion = new Date();
		materialActividadD.setEstatus('E');
		materialActividadD.setFechaDevolucion(fechaDevolucion);
		materialActividadD.getMaterial().setCantidadDisponible(
				materialActividadD.getMaterial().getCantidadDisponible()
						+ materialActividadD.getCantidadDevuelta());
		materialActividadD.getMaterial().setCantidadDeteriorada(
				cantidadMalEstado);
		servicioMaterialActividad.agregar(materialActividadD);
		this.onClick$btnCancelarDevolucion();
		Messagebox.show("Devolucion realizada exitosamente", "Mensaje",
				Messagebox.OK, Messagebox.EXCLAMATION);
		this.onClick$menuTodasP();
		this.onClick$menuTodasD();

	}

	public void onClick$btnCancelarDevolucion() {
		materialActividad = new MaterialActividad();
		materialActividadD = new MaterialActividad();
		materialActividadPlanificada = new MaterialActividadPlanificada();
		cantidadDisponible = 0;
		cantidadFaltante = 0;
		cantidadNecesitada = 0;
		cantidadDevolver = 0;
		cantidadMalEstado = 0;
		binder.loadAll();
		this.onClick$menuTodasP();
		this.onClick$menuTodasD();
		btnRequisicion.setVisible(true);
		txtFaltante.setVisible(false);
		labelRequisicion.setVisible(false);
	}

	public void onClick$btnRequisicion() {

		formRequisicion = Executions.createComponents(
				"/Logistica/Vistas/frmGenerarRequisicion.zul", null, null);

		// ((Listbox) formRequisicion.getFellow("lsbxGenerarR")).setModel(new
		// Bin)
		materialActividad = new MaterialActividad();
		Actividad actividad = new Actividad();

		actividad = servicioActividad.buscarActividad(solicitud
				.getPlanificacionActividad());

		materialActividad.setActividad(actividad);
		materialActividad.setMaterial(solicitud.getMaterial());
		materialActividad.setEstatus('A');
		materialActividad.setCantidadEntregada(cantidadNecesitada);
		materialActividad.getMaterial().setCantidadDisponible(
				cantidadDisponible - cantidadNecesitada);
		materialActividad.setCodigoMaterialActividad(servicioMaterialActividad
				.listar().size());

		formRequisicion.setVariable("material", materialActividad, false);

		formRequisicion.addEventListener("onRequisicionCerrado",
				new EventListener() {

					public void onEvent(Event arg0) throws Exception {
						binder.loadAll();
						arg0.stopPropagation();
					}
				});

	}

	public void onClick$btnPlanificacionActividad() {

		// creamos la instancia del catalogo
		Component catalogoPlanificarActividad = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoActividad.zul", null, null);

		// asigna una referencia del formulario al catalogo.
		catalogoPlanificarActividad.setVariable("frmPrestamoDevolucion",
				frmPrestamoDevolucion, false);
		catalogoPlanificarActividad.setVariable("numero", 1, false);

		// Este metodo se llama cuando se envia la señal desde el catalogo
		frmPrestamoDevolucion.addEventListener("onCatalogoActividadCerradoP",
				new EventListener() {

					public void onEvent(Event arg0) throws Exception {
						actividad = new Actividad();
						actividad = (Actividad) frmPrestamoDevolucion
								.getVariable("actividad", false);
						onClick$menuTodasP();
						binder.loadAll();
						arg0.stopPropagation();
					}
				});
	}

	public void onClick$menuTodasP() {
		try {
			solicitudes = servicioMaterialActividadPlanificada
					.listarPorPrestar(actividad.getPlanificacionActividad());
			binder.loadAll();
		} catch (Exception e) {
			binder.loadAll();
		}
	}

	public void onClick$menuCP() {

		solicitudes = servicioMaterialActividadPlanificada
				.listarPorPrestarCompetencia(actividad
						.getPlanificacionActividad());
		binder.loadAll();
	}

	public void onClick$menuEP() {

		solicitudes = servicioMaterialActividadPlanificada
				.listarPorPrestarEntrenamiento(actividad
						.getPlanificacionActividad());
		binder.loadAll();
	}

	public void onClick$menuEVP() {

		solicitudes = servicioMaterialActividadPlanificada
				.listarPorPrestarEvento(actividad.getPlanificacionActividad());
		binder.loadAll();
	}

	public void onClick$menuMP() {

		solicitudes = servicioMaterialActividadPlanificada
				.listarPorPrestarMantenimiento(actividad
						.getPlanificacionActividad());
		binder.loadAll();
	}

	public void onClick$btnDevolucion() {

		// creamos la instancia del catalogo
		Component catalogoPlanificarActividad = Executions.createComponents(
				"/Logistica/Vistas/frmCatalogoActividad.zul", null, null);

		// asigna una referencia del formulario al catalogo.
		catalogoPlanificarActividad.setVariable("frmPrestamoDevolucion",
				frmPrestamoDevolucion, false);
		catalogoPlanificarActividad.setVariable("numero", 2, false);

		// Este metodo se llama cuando se envia la señal desde el catalogo
		frmPrestamoDevolucion.addEventListener("onCatalogoActividadCerradoD",
				new EventListener() {
					public void onEvent(Event arg0) throws Exception {
						actividadD = new Actividad();
						actividadD = (Actividad) frmPrestamoDevolucion
								.getVariable("actividad", false);

						txtActividadDevolucion.setValue(actividadD
								.getPlanificacionActividad().getDescripcion());
						// alert(actividadD.getPlanificacionActividad()
						// .getDescripcion());
						onClick$menuTodasD();
						binder.loadAll();
						arg0.stopPropagation();
					}

				});
	}

	public void onClick$menuTodasD() {

		materialesActividadesPorDevolver = servicioMaterialActividad
				.listarPorDevolver(actividadD);
		binder.loadAll();
	}

	public void onClick$menuCD() {

		materialesActividadesPorDevolver = servicioMaterialActividad
				.listarPorDevolverCompetencia(actividadD);
		binder.loadAll();
	}

	public void onClick$menuED() {

		materialesActividadesPorDevolver = servicioMaterialActividad
				.listarPorDevolverEntrenamiento(actividadD);
		binder.loadAll();
	}

	public void onClick$menuEVD() {

		materialesActividadesPorDevolver = servicioMaterialActividad
				.listarPorDevolverEvento(actividadD);
		binder.loadAll();
	}

	public void onClick$menuMD() {

		materialesActividadesPorDevolver = servicioMaterialActividad
				.listarPorDevolverMantenimiento(actividadD);
		binder.loadAll();
	}

	public void onClick$panelRequ() {

	}

	public PlanificacionActividad getPlanificacionActividad() {
		return planificacionActividad;
	}

	public void setPlanificacionActividad(
			PlanificacionActividad planificacionActividad) {
		this.planificacionActividad = planificacionActividad;
	}

	public Component getFrmPrestamoDevolucion() {
		return frmPrestamoDevolucion;
	}

	public void setFrmPrestamoDevolucion(Component frmPrestamoDevolucion) {
		this.frmPrestamoDevolucion = frmPrestamoDevolucion;
	}

	public Window getFrmPrestamoDevolucionw() {
		return frmPrestamoDevolucionw;
	}

	public void setFrmPrestamoDevolucionw(Window frmPrestamoDevolucionw) {
		this.frmPrestamoDevolucionw = frmPrestamoDevolucionw;
	}

	public Window getFrmCatalogoPlanificacionActividad() {
		return frmCatalogoPlanificacionActividad;
	}

	public void setFrmCatalogoPlanificacionActividad(
			Window frmCatalogoPlanificacionActividad) {
		this.frmCatalogoPlanificacionActividad = frmCatalogoPlanificacionActividad;
	}

	public List<MaterialActividadPlanificada> getSolicitudes() {
		return solicitudes;

	}

	public void setSolicitudes(List<MaterialActividadPlanificada> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public AnnotateDataBinder getBinder() {
		return binder;
	}

	public void setBinder(AnnotateDataBinder binder) {
		this.binder = binder;
	}

	public DetalleRequisicion getDetalleRequision() {
		return detalleRequision;
	}

	public void setDetalleRequision(DetalleRequisicion detalleRequision) {
		this.detalleRequision = detalleRequision;
	}

	public MaterialActividad getMaterialActividad() {
		return materialActividad;
	}

	public void setMaterialActividad(MaterialActividad materialActividad) {
		this.materialActividad = materialActividad;

	}

	public MaterialActividad getMaterialActividadD() {
		return materialActividadD;
	}

	public void setMaterialActividadD(MaterialActividad materialActividadD) {
		this.materialActividadD = materialActividadD;
	}

	public IServicioMaterialActividad getServicioMaterialActividad() {
		return servicioMaterialActividad;
	}

	public void setServicioMaterialActividad(
			IServicioMaterialActividad servicioMaterialActividad) {
		this.servicioMaterialActividad = servicioMaterialActividad;
	}

	public List<MaterialActividad> getMaterialesActividadesPorDevolver() {
		return materialesActividadesPorDevolver;
	}

	public void setMaterialesActividadesPorDevolver(
			List<MaterialActividad> materialesActividadesPorDevolver) {
		this.materialesActividadesPorDevolver = materialesActividadesPorDevolver;
	}

	public MaterialActividadPlanificada getMaterialActividadPlanificada() {
		return materialActividadPlanificada;
	}

	public void setMaterialActividadPlanificada(
			MaterialActividadPlanificada materialActividadPlanificada) {
		this.materialActividadPlanificada = materialActividadPlanificada;
		// materialActividadPlanificada.getCantidadRequerida();
	}

	public IServicioMaterialActividadPlanificada getServicioMaterialActividadPlanificada() {
		return servicioMaterialActividadPlanificada;
	}

	public void setServicioMaterialActividadPlanificada(
			IServicioMaterialActividadPlanificada servicioMaterialActividadPlanificada) {
		this.servicioMaterialActividadPlanificada = servicioMaterialActividadPlanificada;
	}

	public Integer getCantidadNecesitada() {
		return cantidadNecesitada;
	}

	public void setCantidadNecesitada(Integer cantidadNecesitada) {
		this.cantidadNecesitada = cantidadNecesitada;
	}

	public Integer getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(Integer cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public Integer getCantidadFaltante() {
		return cantidadFaltante;
	}

	public void setCantidadFaltante(Integer cantidadFaltante) {
		this.cantidadFaltante = cantidadFaltante;
	}

	public Integer getCantidadDevolver() {
		return cantidadDevolver;
	}

	public void setCantidadDevolver(Integer cantidadDevolver) {
		this.cantidadDevolver = cantidadDevolver;
	}

	public Integer getCantidadMalEstado() {
		return cantidadMalEstado;
	}

	public void setCantidadMalEstado(Integer cantidadMalEstado) {
		this.cantidadMalEstado = cantidadMalEstado;
	}

	public MaterialActividadPlanificada getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(MaterialActividadPlanificada solicitud) {
		this.solicitud = solicitud;

	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	// public void validar() {
	// cmbTipos.getValue();
	// cmbClasificaciones.getValue();
	// txtDescripcion.getValue();
	// spExistencia.getValue();
	// }

}
