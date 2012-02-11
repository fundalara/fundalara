package controlador.entrenamiento;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import modelo.ActividadCalendario;
import modelo.DatoBasico;
import modelo.Sesion;

import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.api.CalendarEvent;
import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.calendar.impl.SimpleCalendarEvent;
import org.zkoss.util.Locales;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
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
	Caption titulo;
	Timer timer;
	Button btnbtnHoy,btnAtras;
	Calendars calendars;
	Span FDOW;
	String form;
	Listbox filtro;
	Listitem item;

	ServicioActividadCalendario servicioActividadCalendario;
	ServicioDatoBasico servicioDatoBasico;
	/*
	 * 0->Evento no registrado, 1->Evento pendiente, 2->Evento cancelado,
	 * 3->Evento Finalizado
	 */
	String[] color = { "#EE5C42", "#cdbe70", "#FDD017", "#a2cd5a" };
	
	@Override
	public void doAfterCompose(Component component) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(component);
		calendars.setReadonly(false);
		cargarEntrenamientos();
		actualizarRangoCalendario();
	}
	
	public void cargarEntrenamientos() {
		EventosCalendario eventosCalendario = new EventosCalendario();
		DatoBasico tipoActividad = servicioDatoBasico.buscarPorString("ACTIVIDADES DE ENTRENAMIENTO");
		for (ActividadCalendario ac : servicioActividadCalendario.listarPorTipoActividad(tipoActividad)) {
			Date fi = new Date(ac.getFechaInicio().getYear(),ac.getFechaInicio().getMonth(),ac.getFechaInicio().getDate(), ac.getHoraInicio().getHours(),ac.getHoraInicio().getMinutes());
			Date ff = new Date(ac.getFechaCulminacion().getYear(),ac.getFechaCulminacion().getMonth(),ac.getFechaCulminacion().getDate(), ac.getHoraFin().getHours(),ac.getHoraFin().getMinutes());
			String contenido = new String(ac.getDescripcion());
			String colorFondo = new String(ac.getColor());
			Object valor = new Object();
			valor = ac.getSesion();
			SimpleCalendarEvent simpleCalendarEvent = crearEvento(fi, ff, contenido, colorFondo, valor);
			eventosCalendario.cargarEvento(simpleCalendarEvent);
		}
		calendars.setModel(eventosCalendario.getModel());
	}
	
	public SimpleCalendarEvent crearEvento(Date fi, Date ff, 
			String contenido, String colorFondo,
			Object valor) {
			EventoSimpleCalendario esc = new EventoSimpleCalendario();
			esc.setBeginDate(fi);
			esc.setEndDate(ff);	
			esc.setContent(contenido);
			esc.setContentColor(colorFondo);
	        esc.setHeaderColor(colorFondo);
			esc.setValue(valor);
			return esc;
		}
	
	/*Ejemplo como crear un evento*/
	public void onCreate$wndCalendario() {
	}

	public void onEventEdit$calendars(CalendarsEvent event){
        		
//		if (calendars.getMold().equalsIgnoreCase("month")){
//			calendars.setMold("default");
//			calendars.setDays(1);	
//			CalendarEvent evento = event.getCalendarEvent();
//			calendars.setCurrentDate(evento.getBeginDate());
//		}else{
//			
		Window win = new Window();
			EventoSimpleCalendario value = (EventoSimpleCalendario) event.getCalendarEvent();
			win.setAttribute("sesion", value.getValue());
			win = (Window) execution.createComponents(
				"/Entrenamiento/Vistas/frmAsignarInstalaciones.zul", null, null);// remplazo de form por ruta del formulario entre comillas dobles
			win.doHighlighted();
			
			
		//}
	}
	
	/* 
	 * Programacion del toolbar del calendario 
	 * 
	 */
	public void cambiarFormatoCalendario(String vista){
		if (vista.compareTo("Semana") == 0) {
			calendars.setMold("default");
		} else
			calendars.setMold("month");
		FDOW.setVisible("month".equals(calendars.getMold())
				|| calendars.getDays() == 7);
	}
	
	public void onClick$btnAtras(){
		calendars.setMold("month");
	}
	
	public void onClickTabs(ForwardEvent event) {
		String view = String.valueOf(event.getData());
		cambiarFormatoCalendario(view);
		actualizarRangoCalendario();
	}
	
	public void actualizarRangoCalendario(){
		Date fechaIni = calendars.getBeginDate();
		Date fechaFin = calendars.getEndDate();		
		SimpleDateFormat formatoFecha = new SimpleDateFormat(
				"MMMMM/yyyy", Locales.getCurrent());
		formatoFecha.setTimeZone(calendars.getDefaultTimeZone());		 
		rangoCalendario.setValue(formatoFecha.format(fechaIni)+" - "+formatoFecha.format(fechaFin));
	}

	public void onClick$btnHoy(){
		calendars.setCurrentDate(Calendar.getInstance(
				calendars.getDefaultTimeZone()).getTime());
		actualizarRangoCalendario();
	}
	
	public void onCambiarMesSemana(ForwardEvent event){
		if (event.getData().equals("arrow-left"))
			calendars.previousPage();
		else
			calendars.nextPage();
		actualizarRangoCalendario();
	}
}