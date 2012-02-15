package controlador.logistica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Actividad;
import modelo.DetalleRequisicion;
import modelo.MaterialActividad;
import modelo.MaterialActividadPlanificada;
import modelo.PlanificacionActividad;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.interfaz.IServicioActividad;
import servicio.interfaz.IServicioMaterialActividad;
import servicio.interfaz.IServicioMaterialActividadPlanificada;

/**
 * @version , 11/02/2012 Clase controladora de los eventos de la vista de igual
 *          nombre y manejo de los servicios de datos para el control de
 *          prestamo y devolucion de materiales * @author Eduardo Quintero.
 */

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
	Spinner txtDisponibleAsignar;
	Label labelRequisicion;
	Button btnRequisicion;
	Button btnGuardarSolicitud;
	Button btnGuardarDevolucion;
	Component formRequisicion;
	Window formRequisicionW;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, false);
		// solicitudes = servicioMaterialActividadPlanificada.listar();
		// materialesActividadesPorDevolver = servicioMaterialActividad
		// .listarPorDevolver();
		cantidadMalEstado = 0;
	}

	/**
	 * Es una llamada de un evento, y cuando es activado se guarda en el una
	 * instancia de materialActividadPlanificada, ademas que se haran los
	 * calculos para saber la cantidad dispoible de material la cantidad
	 * necesitada en la solicitud y de ser necesario con la cantidad faltante se
	 * registrara la cantidad de material para hacer una requisicion
	 * 
	 * @return materialACtividadPlanificado.
	 */

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

	/**
	 * Es una llamada de un evento, y cuando es activado se guarda en el una
	 * instancia de materialActividad,
	 * 
	 * @return materialACtividad.
	 */
	public void onSelect$lboxDevolucionMaterial() {
		materialActividadD.setCantidadDevuelta(materialActividadD.getCantidadEntregada());
		binder.loadAll();
	}

	/**
	 * es una llamada al evento del boton btnGuardarSolicitud y sirve para
	 * registra un prestamo de material, debe realizarce validaciones como que
	 * se entregue la cantidad pedida, o en su defecto la cantidad maxima
	 * disponible en el inventario
	 * 
	 * @return mensjae de exito o error.
	 */
	public void onClick$btnGuardarSolicitud() throws InterruptedException {

		if (cantidadNecesitada <= cantidadDisponible) {
			if (cantidadNecesitada > solicitud.getCantidadRequerida()) {
				int pregunta2 = JOptionPane.showConfirmDialog(null,
						"La cantidad de material asignado es mayor que la cantida de material pedido, desea continuar? ",

						"Alerta", JOptionPane.YES_NO_OPTION);
				// si respuesta es si
				if (pregunta2 == 0) {
					materialActividad = new MaterialActividad();
					Actividad actividad = new Actividad();

					actividad = servicioActividad.buscarActividad(solicitud.getPlanificacionActividad());

					materialActividad.setCodigoMaterialActividad(servicioMaterialActividad.listar().size() + 1);
					materialActividad.setActividad(actividad);
					materialActividad.setMaterial(solicitud.getMaterial());
					materialActividad.setEstatus('A');
					materialActividad.setCantidadEntregada(cantidadNecesitada);
					materialActividad.getMaterial().setCantidadDisponible(cantidadDisponible - cantidadNecesitada);
					servicioMaterialActividad.agregar(materialActividad);
					solicitud.setEstatus('E');
					servicioMaterialActividadPlanificada.agregar(solicitud);
					// this.onClick$btnCancelarSolicitud();
					cantidadDisponible = 0;
					cantidadFaltante = 0;
					cantidadNecesitada = 0;
					cantidadDevolver = 0;
					cantidadMalEstado = 0;
					this.onClick$menuTodasP();
					this.onClick$menuTodasD();
					btnRequisicion.setVisible(true);
					txtFaltante.setVisible(false);
					labelRequisicion.setVisible(false);
					btnRequisicion.setVisible(false);
					solicitud = new MaterialActividadPlanificada();
					binder.loadAll();
					Messagebox.show("Prestamo realizado exitosamente", "Mensaje", Messagebox.OK, Messagebox.EXCLAMATION);
				} else {
					// si respuesta es no focus al item
					txtDisponibleAsignar.focus();
				}
			} else {
				materialActividad = new MaterialActividad();
				Actividad actividad = new Actividad();

				actividad = servicioActividad.buscarActividad(solicitud.getPlanificacionActividad());

				materialActividad.setCodigoMaterialActividad(servicioMaterialActividad.listar().size() + 1);
				materialActividad.setActividad(actividad);
				materialActividad.setMaterial(solicitud.getMaterial());
				materialActividad.setEstatus('A');
				materialActividad.setCantidadEntregada(cantidadNecesitada);
				materialActividad.getMaterial().setCantidadDisponible(cantidadDisponible - cantidadNecesitada);
				servicioMaterialActividad.agregar(materialActividad);
				solicitud.setEstatus('E');
				servicioMaterialActividadPlanificada.agregar(solicitud);
				// this.onClick$btnCancelarSolicitud();
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
				btnRequisicion.setVisible(false);
				solicitud = new MaterialActividadPlanificada();
				binder.loadAll();
				Messagebox.show("Prestamo realizado exitosamente", "Mensaje", Messagebox.OK, Messagebox.EXCLAMATION);

			}
		} else {
			Messagebox.show("La cantidad de material que desea asigar es superior a la que esta en inventario, por favor verifique", "Mensaje",
					Messagebox.OK, Messagebox.ERROR);
			txtDisponibleAsignar.focus();
		}
	}

	/**
	 * es una llamada al evento del boton btnCancelarSolititud, y funciona para
	 * limpiar y crear una nueva instancias de las variables mostradas en la
	 * pantalla de prestamo de materiales. haciendo que quede como desde un
	 * comienzo
	 * 
	 * @return true.
	 */
	public void onClick$btnCancelarSolicitud() {
		solicitudes = new ArrayList<MaterialActividadPlanificada>();
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
		btnRequisicion.setVisible(false);
		actividad = new Actividad();
		solicitudes = new ArrayList<MaterialActividadPlanificada>();
		// btnGuardarSolicitud.setDisabled(true);
		// btnGuardarDevolucion.setDisabled(true);
	}

	/**
	 * es una llamada al evento del boton btnGuardarDevolucion,y funciona para
	 * guardar las devoluciones de materiales se debe validar que la cantidad
	 * devuelta no sea superior a la entregada que la cantidad dañada no supere
	 * la entregada y que la suma de las dañadas y las devueltas no sean mayor a
	 * la entrada
	 * 
	 * @return mensaje de exito o error.
	 */
	public void onClick$btnGuardarDevolucion() throws InterruptedException {

		if (materialActividadD.getCantidadDevuelta() <= materialActividadD.getCantidadEntregada()) {
			if (cantidadMalEstado <= materialActividadD.getCantidadEntregada()) {
				if (cantidadMalEstado + materialActividadD.getCantidadDevuelta() <= materialActividadD.getCantidadEntregada()) {
					Date fechaDevolucion = new Date();
					materialActividadD.setEstatus('E');
					materialActividadD.setFechaDevolucion(fechaDevolucion);
					materialActividadD.getMaterial().setCantidadDisponible(
							materialActividadD.getMaterial().getCantidadDisponible() + materialActividadD.getCantidadDevuelta());
					materialActividadD.getMaterial().setCantidadDeteriorada(cantidadMalEstado);
					servicioMaterialActividad.agregar(materialActividadD);
					// this.onClick$btnCancelarDevolucion();
					Messagebox.show("Devolucion realizada exitosamente", "Mensaje", Messagebox.OK, Messagebox.EXCLAMATION);
					this.onClick$menuTodasP();
					this.onClick$menuTodasD();
					materialActividadPlanificada = new MaterialActividadPlanificada();
					cantidadDisponible = 0;
					cantidadFaltante = 0;
					cantidadNecesitada = 0;
					cantidadDevolver = 0;
					cantidadMalEstado = 0;
					this.onClick$menuTodasP();
					this.onClick$menuTodasD();
					btnRequisicion.setVisible(true);
					txtFaltante.setVisible(false);
					labelRequisicion.setVisible(false);
					materialActividadD = new MaterialActividad();
					binder.loadAll();
				} else {
					Messagebox.show("La cantidad devuelta mas la cantidad en mal estado supera la cantidad prestada, por favor verifique", "Mensaje",
							Messagebox.OK, Messagebox.EXCLAMATION);

				}

			} else {

				Messagebox.show("La cantidad en mal estado supera la cantidad prestada, por favor verifique", "Mensaje", Messagebox.OK,
						Messagebox.EXCLAMATION);
			}
		} else {
			Messagebox.show("La cantidad entregada supera la cantidad prestada, por favor verifique", "Mensaje", Messagebox.OK,
					Messagebox.EXCLAMATION);
		}

	}

	/**
	 * es una llamada al evento del boton btnCancelarDevolucion, y funciona para
	 * limpiar y crear una nueva instancias de las variables mostradas en la
	 * pantalla de prestamo de materiales. haciendo que quede como desde un
	 * comienzo
	 * 
	 * @return true.
	 */
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
		actividadD = new Actividad();
		materialesActividadesPorDevolver = new ArrayList<MaterialActividad>();

	}

	/**
	 * es una llamada al evento del boton btnRequisicion, cuando la cantidad
	 * pedida es superior a la que existe en inventario aparece este boton lo
	 * que hace es que llamada otro formulario precargada con la cantidad de
	 * material faltante para asi registrar una requisicion de forma rapida y
	 * amigable
	 * 
	 * @return true.
	 * @throws InterruptedException
	 */
	public void onClick$btnRequisicion() throws InterruptedException {

		formRequisicionW = (Window) Executions.createComponents("/Logistica/Vistas/frmGenerarRequisicion.zul", null, null);
		formRequisicionW.setMode("modal");
		formRequisicion = (Component) formRequisicionW;

		// ((Listbox) formRequisicion.getFellow("lsbxGenerarR")).setModel(new
		// Bin)
		materialActividad = new MaterialActividad();
		Actividad actividad = new Actividad();

		actividad = servicioActividad.buscarActividad(solicitud.getPlanificacionActividad());
		materialActividad.setActividad(actividad);
		materialActividad.setMaterial(solicitud.getMaterial());
		materialActividad.setEstatus('A');
		materialActividad.setCantidadEntregada(txtFaltante.getValue());
		// materialActividad.getMaterial().setCantidadDisponible(
		// cantidadDisponible - cantidadNecesitada);
		materialActividad.setCodigoMaterialActividad(servicioMaterialActividad.listar().size() + 1);
		materialActividad.setEstatus('x');

		formRequisicion.setVariable("material", materialActividad, false);

		formRequisicion.addEventListener("onRequisicionCerrado", new EventListener() {

			public void onEvent(Event arg0) throws Exception {
				binder.loadAll();
				arg0.stopPropagation();
			}
		});

	}

	/**
	 * es una llamada al evento del boton btnPlanificacionActividad sirve para
	 * llamar a otro formulario que contiene todas las actividades planificadas,
	 * y asi poder filtrar los materiales por las actividades planificadas
	 * 
	 * @return materialActividadPlanificado.
	 */
	public void onClick$btnPlanificacionActividad() {
		// creamos la instancia del catalogo
		Component catalogoPlanificarActividad = Executions.createComponents("/Logistica/Vistas/frmCatalogoActividad.zul", null, null);

		// asigna una referencia del formulario al catalogo.
		catalogoPlanificarActividad.setVariable("frmPrestamoDevolucion", frmPrestamoDevolucion, false);
		catalogoPlanificarActividad.setVariable("numero", 1, false);

		// Este metodo se llama cuando se envia la señal desde el catalogo
		frmPrestamoDevolucion.addEventListener("onCatalogoActividadCerradoP", new EventListener() {

			public void onEvent(Event arg0) throws Exception {
				actividad = new Actividad();
				actividad = (Actividad) frmPrestamoDevolucion.getVariable("actividad", false);
				onClick$menuTodasP();
				binder.loadAll();
				arg0.stopPropagation();
			}
		});
	}

	/**
	 * es la llamada a un evento del boton menuTodasP y sirve para filtrar los
	 * materiales a prestar por actividades en general
	 * 
	 * @return List<MaterialActividadPlanificado>.
	 */
	public void onClick$menuTodasP() {
		try {
			solicitudes = servicioMaterialActividadPlanificada.listarPorPrestar(actividad.getPlanificacionActividad());
			binder.loadAll();
		} catch (Exception e) {
			binder.loadAll();
		}
	}

	/**
	 * es la llamada a un evento del boton menuCP y sirve para filtrar los
	 * materiales a prestar por actividades de tipo competencia
	 * 
	 * @return List<MaterialActividadPlanificado>.
	 */
	public void onClick$menuCP() {

		try {
			solicitudes = servicioMaterialActividadPlanificada.listarPorPrestarCompetencia(actividad.getPlanificacionActividad());
			binder.loadAll();
		} catch (Exception e) {
			binder.loadAll();
		}
	}

	/**
	 * es la llamada a un evento del boton menuEP y sirve para filtrar los
	 * materiales a prestar por actividades de tipo entrenamientos
	 * 
	 * @return List<MaterialActividadPlanificado>.
	 */
	public void onClick$menuEP() {

		try {
			solicitudes = servicioMaterialActividadPlanificada.listarPorPrestarEntrenamiento(actividad.getPlanificacionActividad());
			binder.loadAll();
		} catch (Exception e) {
			binder.loadAll();
		}
	}

	/**
	 * es la llamada a un evento del boton menuEVP y sirve para filtrar los
	 * materiales a prestar por actividades de tipo evento o actividades
	 * complementarias
	 * 
	 * @return List<MaterialActividadPlanificado>.
	 */
	public void onClick$menuEVP() {

		try {
			solicitudes = servicioMaterialActividadPlanificada.listarPorPrestarEvento(actividad.getPlanificacionActividad());
			binder.loadAll();
		} catch (Exception e) {
			binder.loadAll();
		}
	}

	/**
	 * es la llamada a un evento del boton menuMP y sirve para filtrar los
	 * materiales a prestar por actividades de tipo mantenimientos
	 * 
	 * @return List<MaterialActividadPlanificado>.
	 */
	public void onClick$menuMP() {

		try {
			solicitudes = servicioMaterialActividadPlanificada.listarPorPrestarMantenimiento(actividad.getPlanificacionActividad());
			binder.loadAll();
		} catch (Exception e) {
			binder.loadAll();
		}
	}

	/**
	 * es la llamada a un evento del boton btnDevolucion y llama a otro
	 * formulario que muestra las actividades en desarrollo para filtrar la
	 * devolucion de materiales por actividades en general
	 * 
	 * @return List<MaterialActividad>.
	 */
	public void onClick$btnDevolucion() {

		// creamos la instancia del catalogo
		Component catalogoPlanificarActividad = Executions.createComponents("/Logistica/Vistas/frmCatalogoActividad.zul", null, null);

		// asigna una referencia del formulario al catalogo.
		catalogoPlanificarActividad.setVariable("frmPrestamoDevolucion", frmPrestamoDevolucion, false);
		catalogoPlanificarActividad.setVariable("numero", 2, false);

		// Este metodo se llama cuando se envia la señal desde el catalogo
		frmPrestamoDevolucion.addEventListener("onCatalogoActividadCerradoD", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				actividadD = new Actividad();
				actividadD = (Actividad) frmPrestamoDevolucion.getVariable("actividad", false);

				txtActividadDevolucion.setValue(actividadD.getPlanificacionActividad().getDescripcion());
				// alert(actividadD.getPlanificacionActividad()
				// .getDescripcion());
				onClick$menuTodasD();
				binder.loadAll();
				arg0.stopPropagation();
			}

		});
	}

	/**
	 * es la llamada a un evento del boton menuTodasD y sirve para filtrar la
	 * devolucion de materiales por actividades en general
	 * 
	 * @return List<MaterialActividad>.
	 */
	public void onClick$menuTodasD() {
		try {
			materialesActividadesPorDevolver = servicioMaterialActividad.listarPorDevolver(actividadD);
			binder.loadAll();
		} catch (Exception e) {
			binder.loadAll();
		}
	}

	/**
	 * es la llamada a un evento del boton menuCD y sirve para filtrar la
	 * devolucion de materiales por actividades de competencias
	 * 
	 * @return List<MaterialActividad>.
	 */
	public void onClick$menuCD() {
		try {
			materialesActividadesPorDevolver = servicioMaterialActividad.listarPorDevolverCompetencia(actividadD);
			binder.loadAll();
		} catch (Exception e) {
			binder.loadAll();
		}
	}

	/**
	 * es la llamada a un evento del boton menuED y sirve para filtrar la
	 * devolucion de materiales por actividades de entrenamientos
	 * 
	 * @return List<MaterialActividad>.
	 */
	public void onClick$menuED() {

		try {
			materialesActividadesPorDevolver = servicioMaterialActividad.listarPorDevolverEntrenamiento(actividadD);
			binder.loadAll();
		} catch (Exception e) {
			binder.loadAll();
		}
	}

	/**
	 * es la llamada a un evento del boton menuCD y sirve para filtrar la
	 * devolucion de materiales por actividades de eventos y actividades
	 * complementarias
	 * 
	 * @return List<MaterialActividad>.
	 */
	public void onClick$menuEVD() {
		try {
			materialesActividadesPorDevolver = servicioMaterialActividad.listarPorDevolverEvento(actividadD);
			binder.loadAll();
		} catch (Exception e) {
			binder.loadAll();
		}
	}

	/**
	 * es la llamada a un evento del boton menuCD y sirve para filtrar la
	 * devolucion de materiales por actividades de mantenimiento
	 * 
	 * @return List<MaterialActividad>.
	 */
	public void onClick$menuMD() {

		try {
			materialesActividadesPorDevolver = servicioMaterialActividad.listarPorDevolverMantenimiento(actividadD);
			binder.loadAll();
		} catch (Exception e) {
			binder.loadAll();
		}
	}

	/**
	 * seccion de getter y setters
	 * 
	 */
	public PlanificacionActividad getPlanificacionActividad() {
		return planificacionActividad;
	}

	public void setPlanificacionActividad(PlanificacionActividad planificacionActividad) {
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

	public void setFrmCatalogoPlanificacionActividad(Window frmCatalogoPlanificacionActividad) {
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

	public void setServicioMaterialActividad(IServicioMaterialActividad servicioMaterialActividad) {
		this.servicioMaterialActividad = servicioMaterialActividad;
	}

	public List<MaterialActividad> getMaterialesActividadesPorDevolver() {
		return materialesActividadesPorDevolver;
	}

	public void setMaterialesActividadesPorDevolver(List<MaterialActividad> materialesActividadesPorDevolver) {
		this.materialesActividadesPorDevolver = materialesActividadesPorDevolver;
	}

	public MaterialActividadPlanificada getMaterialActividadPlanificada() {
		return materialActividadPlanificada;
	}

	public void setMaterialActividadPlanificada(MaterialActividadPlanificada materialActividadPlanificada) {
		this.materialActividadPlanificada = materialActividadPlanificada;
		// materialActividadPlanificada.getCantidadRequerida();
	}

	public IServicioMaterialActividadPlanificada getServicioMaterialActividadPlanificada() {
		return servicioMaterialActividadPlanificada;
	}

	public void setServicioMaterialActividadPlanificada(IServicioMaterialActividadPlanificada servicioMaterialActividadPlanificada) {
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

}