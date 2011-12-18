package controlador.general;

import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.impl.SimpleCalendarEvent;
import org.zkoss.calendar.impl.SimpleCalendarModel;

public class EventosCalendario {	
	public SimpleCalendarModel model = new SimpleCalendarModel();
	
	public void cargarEventos(SimpleCalendarEvent ce){
		model.add(ce);
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
