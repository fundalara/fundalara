package controlador.general;

import java.util.Date;
import java.util.Iterator;

import modelo.Actividad;
import modelo.DatoBasico;
import modelo.EstadoActividad;
import modelo.PlanificacionActividad;

import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.calendar.impl.SimpleCalendarEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import servicio.interfaz.IServicioActividad;
import servicio.interfaz.IServicioPlanificacionActividad;

import comun.MensajeMostrar;

/**
 * Clase controladora de los eventos de la agenda para la planificación y
 * registro de resultados de actividades complementarias y mantenimientos
 * 
 * @author Reinaldo L.
 * 
 * */
public class CntrlFrmAgendaLogistica extends CntrlFrmAgendaGeneral {

	private String opcionMenu;
	public EventosCalendario ec;
	public Window frmAgenda;
	private Window frmPlanificar;
	private Datebox frmPlanificar$fechaInicio;
	private Datebox frmPlanificar$fechaFin;
	private Timebox frmPlanificar$horaInicio;
	private Timebox frmPlanificar$horaFin;
	private IServicioPlanificacionActividad servicioPlanificacionActividad;
	private IServicioActividad servicioActividad;

	@Override
	public void doAfterCompose(Component component) throws Exception {
		super.doAfterCompose(component);

		frmAgenda = (Window) component;

		// configurando visualizacion
		calendars.setMold("default");
		calendars.setDays(7);
		calendars.setReadonly(false);

		opcionMenu = (String) frmAgenda.getAttribute("opcion");

		if (opcionMenu.equals("ActividadComplementaria")) {
			cargarCalendario();
			calendars.setTooltiptext("Haga click sobre una fecha para planificar una actividad");
			Messagebox.show("Haga click sobre una fecha para planificar una actividad", MensajeMostrar.TITULO + "Información", Messagebox.OK,
					Messagebox.INFORMATION);
		} else {
			cargarCalendario();
			calendars.setTooltiptext("Haga click sobre una fecha para planificar un mantenimiento");
			Messagebox.show("Haga click sobre una fecha para planificar un mantenimiento", MensajeMostrar.TITULO + "Información", Messagebox.OK,
					Messagebox.INFORMATION);
		}
	}

	/**
	 * Carga una lista de eventos de tipo PlanificacionActividad en el
	 * calendario
	 * 
	 * @param eventos
	 *            Lista de Actividades Complementarias o de Mantenimiento
	 */
	public void cargarCalendario() {

		SimpleCalendarEvent evento;
		ec = new EventosCalendario();

		for (Actividad actividad : servicioActividad.listarComplementarias()) {
			System.out.println(actividad.getPlanificacionActividad().getDescripcion());
			Date fi = new Date(actividad.getFechaInicio().getTime() + actividad.getHoraInicio().getTime() - 18000000);
			Date ff = new Date(actividad.getFechaCulminacion().getTime() + actividad.getHoraFin().getTime() - 18000000);
			String color = obtenerColor(actividad);
			evento = crearEvento(fi, ff, actividad.getPlanificacionActividad().getDescripcion(), color, actividad.getPlanificacionActividad());
			// ec.cargarEvento(evento);
			ec.getModel().update(evento);
		}

		calendars.setModel(ec.getModel());

	}

	/**
	 * Asigna un color en el calendario a una actividad dependiendo de su estado
	 * 
	 * @param actividad
	 *            a la cual se desea asignar un color en el calendario
	 * 
	 * @return color asignado dependiendo del estado de la actividad
	 */
	public String obtenerColor(Actividad actividad) {

		EstadoActividad estatus = new EstadoActividad();
		DatoBasico estado = new DatoBasico();

		Iterator<EstadoActividad> i = actividad.getEstadoActividads().iterator();
		while (i.hasNext()) {
			estatus = i.next();
			if (estatus.getEstatus() == 'A') {
				estado = estatus.getDatoBasico();
			}
		}
		if (estado.getNombre() != null) {
			if (estado.getNombre().equals("SUSPENDIDA")) {
				// System.out.println("suspendida");
				return "#F13616";
			} else if (estado.getNombre().equals("TERMINADA")) {
				// System.out.println("completada con resultados ya registrados");
				return "#FDD017";
			} else if (actividad.getFechaInicio().after(new Date())) {
				// System.out.println("programada por realizarse");
				return "#32CD32";
			} else {
				// System.out.println("culminada sin registrar resultados");
				return "FF7700";
			}
		} else {
			return null;
		}
	}

	public void onEventCreate$calendars(ForwardEvent event) throws InterruptedException {

		CalendarsEvent ce = (CalendarsEvent) event.getOrigin();

		if (ce.getBeginDate().after(new Date())) {

			if (opcionMenu.equals("ActividadComplementaria")) {
				planificar(event, "../Logistica/Vistas/frmPlanificarActividad.zul");
			} else {
				planificar(event, "../Logistica/Vistas/frmPlanificarMantenimiento.zul");
			}
		} else {
			Messagebox.show("La planificación no es permitida en fechas anteriores a la actual", MensajeMostrar.TITULO + "Información",
					Messagebox.OK, Messagebox.INFORMATION);
		}
	}

	public void onEventEdit$calendars(CalendarsEvent e) {
		System.out.println("editando las actividades");
		if (opcionMenu.equals("ActividadComplementaria")) {
			registrarResultados(e, "../Logistica/Vistas/frmResultadosActividadComplementaria.zul");
		} else {
			registrarResultados(e, "../Logistica/Vistas/frmResultadosMantenimiento.zul");
		}
	}

	/**
	 * Método invocado al seleccionar una actividad del calendario para realizar
	 * su seguimiento
	 * 
	 * @param evento
	 *            al cual se desea hacer seguimiento y registrar resultados
	 * 
	 * @param url
	 *            del formulario a invocar dependiendo si es una actividad
	 *            complementaria o un mantenimiento
	 */
	public void registrarResultados(CalendarsEvent evento, String url) {
		EventoSimpleCalendario esc = (EventoSimpleCalendario) evento.getCalendarEvent();
		Window w = (Window) Executions.createComponents(url, null, null);
		w.setPosition("center");
		PlanificacionActividad planificacionActividad = (PlanificacionActividad) esc.getValue();

		System.out.println(planificacionActividad.getCodigoPlanificacionActividad());
		w.setVariable("planificacionActividad", planificacionActividad, false);
		w.setVariable("esc", esc, false);
		w.setVariable("agenda", this, false);
	}

	/**
	 * Recarga el modelo del calendario luego de registrar los resultados de una
	 * actividad
	 * 
	 * @param esc
	 *            referencia al evento sobre el cual se registraron resultados y
	 *            cuyo color en el calendario debe cambiar
	 */
	public void recargarModelo(SimpleCalendarEvent esc) {
		calendars.setModel(ec.getModel());
	}

	/**
	 * Método invocado al hacer click sobre el calendario para planificar una
	 * actividad en una fecha y hora dada
	 * 
	 * @param evento
	 *            con información de la hora y fecha seleccionada para
	 *            planificar la actividad
	 * 
	 * @param url
	 *            del formulario a invocar dependiendo si es una actividad
	 *            complementaria o un mantenimiento
	 */
	public void planificar(ForwardEvent evento, String url) {

		frmPlanificar = (Window) Executions.createComponents(url, null, null);

		frmPlanificar.setAttribute("calendario", this);

		CalendarsEvent ce = (CalendarsEvent) evento.getOrigin();

		frmPlanificar$fechaInicio = (Datebox) frmPlanificar.getFellow("fechaInicio");
		frmPlanificar$fechaFin = (Datebox) frmPlanificar.getFellow("fechaFin");
		frmPlanificar$horaInicio = (Timebox) frmPlanificar.getFellow("horaInicio");
		frmPlanificar$horaFin = (Timebox) frmPlanificar.getFellow("horaFin");

		Date fechaInicio = ce.getBeginDate();
		fechaInicio = new Date(fechaInicio.getYear(), fechaInicio.getMonth(), fechaInicio.getDate());
		Date fechaFin = ce.getEndDate();
		fechaFin = new Date(fechaFin.getYear(), fechaFin.getMonth(), fechaFin.getDate());

		frmPlanificar$fechaInicio.setValue(fechaInicio);
		frmPlanificar$fechaFin.setValue(fechaFin);

		frmPlanificar$horaInicio.setValue(ce.getBeginDate());
		frmPlanificar$horaFin.setValue(ce.getEndDate());

		frmPlanificar.setAttribute("ce", ce);

	}

	/**
	 * Recarga el modelo del calendario luego de planificar una actividad sobre
	 * éste
	 * 
	 * @param ce
	 *            referencia al evento con información de fecha y hora
	 *            seleccionadas en el calendario
	 * @param pa
	 *            referencia de tipo PlanificacionActividad al objeto creado una
	 *            vez realizada la planificación
	 * 
	 */
	public void cargar(CalendarsEvent ce, PlanificacionActividad pa) {
		SimpleCalendarEvent evento = crearEvento(ce.getBeginDate(), ce.getEndDate(), pa.getDescripcion(), "#32CD32", pa);
		ec.cargarEvento(evento);

		calendars.setModel(ec.getModel());
	}

}
