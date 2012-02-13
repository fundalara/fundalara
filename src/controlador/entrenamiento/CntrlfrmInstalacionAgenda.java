package controlador.entrenamiento;

/*
 * 
 * Cambiar el metodo listarPorTipoActividad en el servicioActividadCalendario
 * Agregar en la clase EventosCalendarios el metodo actualizarEvento
 * Estatus para manejar colores
 * A -> Verde; Cualquier otro ->Rojo
 * */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import modelo.ActividadCalendario;
import modelo.DatoBasico;

import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.calendar.impl.SimpleCalendarEvent;
import org.zkoss.util.Locales;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Span;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioActividadCalendario;
import servicio.implementacion.ServicioDatoBasico;

import controlador.general.EventoSimpleCalendario;
import controlador.general.EventosCalendario;

public class CntrlfrmInstalacionAgenda extends GenericForwardComposer {
	Popup updateMsg;
	Label popupLabel, rangoCalendario;
	Timer timer;
	Calendars calendars;
	Span FDOW;
	String form;
	Listbox filtro;
	Listitem item;
	Component componente;
	EventosCalendario eventosCalendario = new EventosCalendario();

	ServicioActividadCalendario servicioActividadCalendario;
	ServicioDatoBasico servicioDatoBasico;
	/*
	 * 0->por registrar, 1->registrado, 2->pendientes, 3->suspendidos
	 */
	String[] color = { "#32CD32", "#FDD017", "#ff7700", "#f13616" };

	@Override
	public void doAfterCompose(Component component) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(component);
		componente = component;
		calendars.setReadonly(false);
		cargarEntrenamientos();
		actualizarRangoCalendario();
	}

	public void cargarEntrenamientos() {		
		DatoBasico tipoActividad = servicioDatoBasico
				.buscarPorString("ACTIVIDADES DE ENTRENAMIENTO");
		List<ActividadCalendario> listac = new ArrayList<ActividadCalendario>();
		listac = servicioActividadCalendario
				.listarPorTipoActividad(tipoActividad);
		if (!listac.isEmpty()) {
			ActividadCalendario auxMenorAc = new ActividadCalendario();
			Date fechaActividadCalendario = new Date();
			fechaActividadCalendario = listac.get(0).getFechaInicio();
			Date hi = new Date();
			Date hf = new Date();
			String contenido = new String();
			String colorFondo = new String();
			ArrayList<ActividadCalendario> listDia = new ArrayList<ActividadCalendario>();
			boolean primero = true;
			for (ActividadCalendario ac : listac) {
				if (fechaActividadCalendario.equals(ac.getFechaInicio())) {
					if (primero) {
						hi = new Date(ac.getFechaInicio().getYear(), ac
								.getFechaInicio().getMonth(), ac
								.getFechaInicio().getDate(), ac.getHoraInicio()
								.getHours(), ac.getHoraInicio().getMinutes());
						primero = !primero;
					}
					hf = new Date(ac.getFechaCulminacion().getYear(), ac
							.getFechaCulminacion().getMonth(), ac
							.getFechaCulminacion().getDate(), ac.getHoraFin()
							.getHours(), ac.getHoraFin().getMinutes());
					listDia.add(ac);
					contenido = contenido
							+ " "
							+ ac.getSesion().getEquipo().getCategoria()
									.getNombre();					
				} else {
					colorFondo = color[0];
					for (ActividadCalendario actividadCalendario : listDia) {
						if (actividadCalendario.getEstatus() != 'A'){
							colorFondo = color[1];
							break;
						}
					}
					System.out.println(hi+" "+hf);
					for (ActividadCalendario actividadCalendario : listDia) {
						System.out.print(actividadCalendario.getHoraInicio()+" "+actividadCalendario.getHoraFin());
						System.out.println(actividadCalendario.getSesion().getEquipo().getNombre());
					}
					primero = !primero;
					SimpleCalendarEvent simpleCalendarEvent = crearEvento(hi,
							hf, contenido, colorFondo, listDia.clone());
					eventosCalendario.cargarEvento(simpleCalendarEvent);
					fechaActividadCalendario = new Date();
					contenido = new String();
					colorFondo = new String();
					listDia.clear();
					fechaActividadCalendario = ac.getFechaInicio();
					if (primero) {
						hi = new Date(ac.getFechaInicio().getYear(), ac
								.getFechaInicio().getMonth(), ac
								.getFechaInicio().getDate(), ac.getHoraInicio()
								.getHours(), ac.getHoraInicio().getMinutes());
						primero = !primero;
					}
					hf = new Date(ac.getFechaCulminacion().getYear(), ac
							.getFechaCulminacion().getMonth(), ac
							.getFechaCulminacion().getDate(), ac.getHoraFin()
							.getHours(), ac.getHoraFin().getMinutes());
					contenido = contenido
							+ " "
							+ ac.getSesion().getEquipo().getCategoria()
									.getNombre();
					listDia.add(ac);
				}
			}
			calendars.setModel(eventosCalendario.getModel());
		}
	}

	public SimpleCalendarEvent crearEvento(Date fi, Date ff, String contenido,
			String colorFondo, Object valor) {
		EventoSimpleCalendario esc = new EventoSimpleCalendario();
		esc.setBeginDate(fi);
		esc.setEndDate(ff);
		esc.setContent(contenido);
		esc.setContentColor(colorFondo);
		esc.setHeaderColor(colorFondo);
		esc.setValue(valor);
		return esc;
	}

	public void onEventEdit$calendars(CalendarsEvent event) {
		Window window = new Window();
		EventoSimpleCalendario value = (EventoSimpleCalendario) event
				.getCalendarEvent();
		execution.setAttribute("actividadCalendario", value.getValue());
		execution.setAttribute("evento", value);
		execution.setAttribute("controlador", this);
		window = (Window)execution.createComponents("/Entrenamiento/Vistas/frmAsignarInstalaciones.zul", null, null);
		window.doHighlighted();
	}
	
	/*Metodo para ser llamado desde otra pantalla*/	
	public void actualizarEvento(SimpleCalendarEvent event){
		eventosCalendario.actualizarEvento(event);
		eventosCalendario.setModel(eventosCalendario.getModel());
	}

	/*
	 * Programacion del toolbar del calendario
	 */
	public void cambiarFormatoCalendario(String vista) {
		if (vista.compareTo("Semana") == 0) {
			calendars.setMold("default");
		} else
			calendars.setMold("month");
		FDOW.setVisible("month".equals(calendars.getMold())
				|| calendars.getDays() == 7);
	}

	public void onClickTabs(ForwardEvent event) {
		String view = String.valueOf(event.getData());
		cambiarFormatoCalendario(view);
		actualizarRangoCalendario();
	}

	public void actualizarRangoCalendario() {
		Date fechaIni = calendars.getBeginDate();
		Date fechaFin = calendars.getEndDate();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("MMMMM/yyyy",
				Locales.getCurrent());
		formatoFecha.setTimeZone(calendars.getDefaultTimeZone());
		rangoCalendario.setValue(formatoFecha.format(fechaIni) + " - "
				+ formatoFecha.format(fechaFin));
	}

	public void onClick$btnHoy() {
		calendars.setCurrentDate(Calendar.getInstance(
				calendars.getDefaultTimeZone()).getTime());
		actualizarRangoCalendario();
	}

	public void onCambiarMesSemana(ForwardEvent event) {
		if (event.getData().equals("arrow-left"))
			calendars.previousPage();
		else
			calendars.nextPage();
		actualizarRangoCalendario();
	}
}