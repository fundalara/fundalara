package controlador.general;

import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.impl.SimpleCalendarEvent;
import org.zkoss.calendar.impl.SimpleCalendarModel;

public class EventosCalendario {	
	public SimpleCalendarModel model = new SimpleCalendarModel();
	
	public void cargarEvento(SimpleCalendarEvent ce){
		model.add(ce);
	}
	
	public void limpiarEventos(){
		int cant  = model.size();
		for (int i = 0; i < cant; i++) {
			model.remove(0);
		}		
	}
	
	public void actualizarEvento (SimpleCalendarEvent ce){
		model.update(ce);
	}
	public SimpleCalendarModel getModel() {
		return model;
	}

	public void setModel(SimpleCalendarModel model) {
		this.model = model;
	}

	public void crearEvent(){
		
	}
}
