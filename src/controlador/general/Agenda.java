package controlador.general;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



import modelo.Competencia;
import modelo.Juego;

import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.calendar.impl.SimpleCalendarEvent;
import org.zkoss.calendar.impl.SimpleCalendarModel;
import org.zkoss.util.Locales;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Span;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioCompetencia;

import comun.EstadoCompetencia;

public class Agenda extends GenericForwardComposer {
	
	
	Popup updateMsg;
	Label popupLabel, rangoCalendario;
	Caption titulo;
	Timer timer;
	Button hoy;
	
	Calendars calendars;
	Span FDOW;
	String form;
	Listbox lsbxFiltro;
	Listitem item;
	
	//Competencias
	Component formulario;
	Competencia competencia;
	Button btnVer;
	ServicioCompetencia servicioCompetencia;

	
	
	/*
	 * 0->Evento no registrado, 1->Evento pendiente, 2->Evento cancelado,
	 * 3->Evento Finalizado
	 */
	String[] color = { "#EE5C42", "#cdbe70", "#FDD017", "#a2cd5a" };

	@Override
	public void doAfterCompose(Component component) throws Exception {
		super.doAfterCompose(component);
		formulario = component;
		actualizarRangoCalendario();
		lsbxFiltro.setSelectedIndex(0);
	
	}

	public void onSelect$lsbxFiltro(){


		switch (lsbxFiltro.getSelectedIndex()){
		
		case 1:{ //Entrenamiento
			calendars.setReadonly(false);
			form = "Entrenamiento/Vistas/Auxiliar_Agenda.zul";	
			break;
		}
		case 2:{ //Competencia
            //Div divMenu = (Div) formulario.getPage().getDesktop().getPage("frmMenu").getFellow("divMenu");
            //divMenu.setVisible(false);
            //Window w = (Window) formulario;
            //w.setWidth("980px");
			calendars.setMold("month");
			calendars.setReadonly(false);
			btnVer.setVisible(true);
			break;
		}	
		case 3:{//mantenimiento
			calendars.setReadonly(false);
			btnVer.setVisible(false);
			calendars.setMold("default");
			break;
		}
		case 4:{//Actividad Complementaria
			calendars.setReadonly(false);
			btnVer.setVisible(false);
			calendars.setMold("default");
			break;
		}
		}
	}
	
	public void onClick$btnVer(){
		
		Component catalogo = Executions.createComponents("/Competencias/Vistas/FrmSelectorCompetencia.zul",null,null);        
        catalogo.setVariable("formulario",formulario,false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				  competencia = (Competencia) formulario.getVariable("competencia",false);		
				  cargarJuegos();
			}
		});
	}
	
	
	public void cargarJuegos(){
		Set<Juego> juegos = competencia.getJuegos();
		EventosCalendario eventosCalendario = new EventosCalendario();
		
		for (Iterator i = juegos.iterator(); i.hasNext();){
			Juego j = (Juego) i.next();			
	        
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(j.getFecha());
			cal2.setTime(j.getFecha());
			cal1.set(Calendar.HOUR, 1);
			cal2.set(Calendar.HOUR, 3);
			System.out.println(cal1.getTime());
			System.out.println(cal2.getTime());
			
			SimpleCalendarEvent e = crearEvento(cal1.getTime(),cal2.getTime(),"f","Fundalara A vs Delfines",color[0],color[0]);
			eventosCalendario.cargarEvento(e);
		}
		calendars.setModel(eventosCalendario.getModel());
	}

	
	public SimpleCalendarEvent crearEvento(Date fi,Date ff,String titulo,String contenido,String colorFondo,String colorEncabezado){
		SimpleCalendarEvent ce = new SimpleCalendarEvent();
		ce.setBeginDate(fi);
		ce.setEndDate(ff);
		ce.setTitle(titulo);
		ce.setContent(contenido);
		ce.setContentColor(colorFondo);
		ce.setHeaderColor(colorEncabezado);
		return ce;
	}
	 
	/*Ejemplo como crear un evento*/
	public void onCreate$wndCalendario() {
	
//		SimpleCalendarEvent ce = new SimpleCalendarEvent();
//		Date di = new Date(12, 01, 01, 10, 0);
//		Date df = new Date(12, 01, 01, 13, 0);
//		ce.setBeginDate(di);
//		ce.setEndDate(df);
//		ce.setContent("Leyner");
//		ce.setTitle("dasdsa dsadsad adsdasd asdsada saddasdas");
//		ce.setContentColor(color[0]);
//		ce.setHeaderColor(color[0]);
//	
//		
//		EventosCalendario calendarEvents = new EventosCalendario();
//		calendarEvents.cargarEvento(ce);
//		calendars.setModel(calendarEvents.getModel());
	}

	public void onEventCreate$calendars(CalendarsEvent event){
		
	}
	
	//Se invoca cuando se da click en el calendario para crear un evento nuevo
	public void onEventCreate$calendars(ForwardEvent event) {
		
		switch (lsbxFiltro.getSelectedIndex()){
		
		case 1:{ //Entrenamiento	
			break;
		}
		case 2:{ //Competencia	
			break;
		}	
		case 3:{//Mantenimiento			
			Component c = Executions.createComponents("Logistica/Vistas/frmPlanificarMantenimiento.zul", null, null);
			break;
		}
		case 4:{//Actividad Complementaria
			Component c = Executions.createComponents("Logistica/Vistas/frmPlanificarActividad.zul", null, null);
			break;
		}
		}
	}
	
	public void onEventEdit$calendars(){
//		Window win = (Window) execution.createComponents(
//				form, null, null);
//		win.doHighlighted();
	}
	
	/* 
	 * Programacion del toolbar del calendario 
	 * 
	 */
	public void cambiarFormatoCalendario(String vista){
		if (vista.compareTo("Semana") == 0) {
			calendars.setMold("default");
			calendars.setDays(7);
		} else if (vista.compareTo("Dia") == 0){
			calendars.setMold("default");
			calendars.setDays(1);
		} else{
			calendars.setMold("month");
		}
		/*Esto todavia no se que es. Sino sirve, quitarse*/
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