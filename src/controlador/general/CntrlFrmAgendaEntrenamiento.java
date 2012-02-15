package controlador.general;

/**
 * Controlador de la Agenda de Entrenamientos Para 
 * mostrar los eventos/calendarios y los componentes visuales
 * para el manejo y registro de los entrenamientos planificados 
 * y ejecutados
 * @version 1.0, 15/02/12
 * @author Leyner Castillo
 */

import java.util.Date;

import modelo.ActividadCalendario;

import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.calendar.impl.SimpleCalendarEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioActividadCalendario;

public class CntrlFrmAgendaEntrenamiento extends CntrlFrmAgendaGeneral {
	ServicioActividadCalendario servicioActividadCalendario;
	EventosCalendario eventosCalendario = new EventosCalendario();
	String[] color = { "#32CD32", "#FDD017", "#FFA500", "#f13616" };

	@Override
	public void doAfterCompose(Component component) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(component);
		calendars.setReadonly(false);
		cargarEntrenamientos();
	}
	
	
	public String getColor(Integer num) {
		return color[num];
	}


	public void setColor(String[] color) {
		this.color = color;
	}


	public String colorActividades (Character estatus){
		if (estatus.equals('A'))
			return color[0];
		else if (estatus.equals('I'))
			return color[0];
		else if (estatus.equals('C'))
			return color[2];
		else if (estatus.equals('F'))
			return color[1];
		else if (estatus.equals('S'))
			return color[3];
		else
			return color[0];	
	}
	
	public void cargarEntrenamientos() {
		EventosCalendario eventosCalendario = new EventosCalendario();
		for (ActividadCalendario ac : servicioActividadCalendario.listar()) {
			if (ac.getEstatus()!='P'){
				Date fi = new Date(ac.getFechaInicio().getYear(),ac.getFechaInicio().getMonth(),ac.getFechaInicio().getDate(), ac.getHoraInicio().getHours(),ac.getHoraInicio().getMinutes());
				Date ff = new Date(ac.getFechaCulminacion().getYear(),ac.getFechaCulminacion().getMonth(),ac.getFechaCulminacion().getDate(), ac.getHoraFin().getHours(),ac.getHoraFin().getMinutes());
				String contenido = new String(ac.getDescripcion());
				String colorFondo = new String(colorActividades(ac.getEstatus()));
				Object valor = new Object();
				valor = ac;
				SimpleCalendarEvent simpleCalendarEvent = crearEvento(fi, ff, contenido, colorFondo, valor);
				eventosCalendario.cargarEvento(simpleCalendarEvent);	
			}
			
		}
		calendars.setModel(eventosCalendario.getModel());
	}
	public void reiniciarModelo(){
		eventosCalendario.limpiarEventos();
		//calendars.setModel(eventosCalendario.getModel());
		cargarEntrenamientos();
	}
	public void onEventEdit$calendars(CalendarsEvent e) throws InterruptedException {
		EventoSimpleCalendario esc = (EventoSimpleCalendario) e.getCalendarEvent();
		ActividadCalendario ac = (ActividadCalendario) esc.getValue();
		Window win = new Window();
		EventoSimpleCalendario value = (EventoSimpleCalendario) e.getCalendarEvent();
		execution.setAttribute("actividadCalendario", value.getValue());
		execution.setAttribute("evento", value);
		execution.setAttribute("controlador", this);
		
		if (ac.getEstatus()=='A')
			Messagebox.show("El entrenamiento no tiene asignada ninguna Instalacion", "Olimpo - Información", Messagebox.OK, Messagebox.INFORMATION);
		else if(ac.getEstatus()=='F')
			Messagebox.show("El entrenamiento posee registrado todos sus resultados", "Olimpo - Información", Messagebox.OK, Messagebox.INFORMATION);
		else if(ac.getEstatus()=='S'){
//			execution.setAttribute("actividad",ac);
			win = (Window)execution.createComponents("/Entrenamiento/Vistas/frmCumplimientoEntrenamiento.zul", null, null);
			win.doHighlighted();
			win.setPosition("center");
		}
		else if (ac.getEstatus()=='C'){
			win = (Window)execution.createComponents("/Entrenamiento/Vistas/frmDesempennoJugador.zul", null, null);
			win.doHighlighted();
			win.setPosition("center");
		}
		else if (ac.getEstatus()=='I'){			
			win = (Window)execution.createComponents("/Entrenamiento/Vistas/frmCumplimientoEntrenamiento.zul", null, null);
			win.doHighlighted();
			win.setPosition("center");
		}
		else {
			execution.setAttribute("actividad",ac);
		  	Window w = new Window();    
			w = (Window) Executions.createComponents("/Entrenamiento/Vistas/frmAuxiliarAgenda.zul", null, null);
	        w.doHighlighted();
		}   
		

		
	}
	
	public void actualizarEvento(SimpleCalendarEvent event){
		eventosCalendario.actualizarEvento(event);
		eventosCalendario.setModel(eventosCalendario.getModel());
	}
}
