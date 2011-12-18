package controlador.general;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.impl.SimpleCalendarEvent;
import org.zkoss.calendar.impl.SimpleCalendarModel;
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

public class Agenda extends GenericForwardComposer {
	Popup updateMsg;
	Label popupLabel, rangoCalendario;
	Caption titulo;
	Timer timer;
	Button hoy;
	Calendars calendars;
	Span FDOW;
	String form;
	Listbox filtro;
	Listitem item;
	/*
	 * 0->Evento no registrado, 1->Evento pendiente, 2->Evento cancelado,
	 * 3->Evento Finalizado
	 */
	String[] color = { "#EE5C42", "#cdbe70", "#FDD017", "#a2cd5a" };

	@Override
	public void doAfterCompose(Component component) throws Exception {
		super.doAfterCompose(component);
		actualizarRangoCalendario();
	}

	public void onSelect$filtro(){
		filtro.removeItemAt(item.getIndex());
		if (filtro.getSelectedItem().getLabel().compareTo("Entrenamiento") == 0) {
			calendars.setReadonly(false);
			form = "Entrenamiento/Vistas/Auxiliar_Agenda.zul";
		}
	}

	/*Ejemplo como crear un evento*/
	public void onCreate$wndCalendario() {
		SimpleCalendarEvent ce = new SimpleCalendarEvent();
		Date di = new Date(111, 11, 21, 11, 0);
		Date df = new Date(111, 11, 24, 13, 0);
		ce.setBeginDate(di);
		ce.setEndDate(df);
		ce.setContent("Leyner");
		ce.setTitle("Titulo");
		ce.setContentColor(color[0]);
		ce.setHeaderColor(color[0]);
		
		EventosCalendario calendarEvents = new EventosCalendario();
		calendarEvents.cargarEventos(ce);
		calendars.setModel(calendarEvents.getModel());
	}

	public void onEventEdit$calendars(){
		Window win = (Window) execution.createComponents(
				form, null, null);
		win.doHighlighted();
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

	public void onClick$hoy(){
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