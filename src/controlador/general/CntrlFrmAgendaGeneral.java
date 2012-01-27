package controlador.general;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.impl.SimpleCalendarEvent;
import org.zkoss.util.Locales;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Span;


public class CntrlFrmAgendaGeneral extends GenericForwardComposer {
	
	
	Button hoy;
	Component formulario;
	Calendars calendars;
	Label rangoCalendario;
	Span spnControles;
	String[] color = { "#EE5C42", "#32CD32", "#FDD017", "#FFA500" };
	
	
	
	@Override
	public void doAfterCompose(Component component) throws Exception {
		super.doAfterCompose(component);
		formulario = component;
		actualizarRangoCalendario();		
	}
		
	public void onClose$frmAgenda(){
		Div divMenu = (Div) formulario.getPage().getDesktop().getPage("frmMenu").getFellow("divMenu");
		divMenu.setVisible(true);
	}
	
	public void cambiarFormatoCalendario(String vista) {
		if (vista.compareTo("Semana") == 0) {
			calendars.setMold("default");
			calendars.setDays(7);
		} else if (vista.compareTo("Dia") == 0) {
			calendars.setMold("default");
			calendars.setDays(1);
		} else {
			calendars.setMold("month");
		}
		/* Esto todavia no se que es. Sino sirve, quitarse */
		spnControles.setVisible("month".equals(calendars.getMold())
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


	public void onClick$hoy() {
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
	
	public String obtenerColor(String estatus){
		 if (estatus.equals("POR REALIZAR")){
			 return color[1];
		 }else if(estatus.equals("CULMINADO")){
			 return color[3];
		 }else{
			 return color[0];
		 }
	}

}
