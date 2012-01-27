<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
package controlador.general;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.Competencia;
import modelo.EquipoJuego;
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
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Span;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioCompetencia;
import servicio.implementacion.ServicioEquipoJuego;

import comun.EstadoCompetencia;

public class CntrlFrmAgenda extends GenericForwardComposer {

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

	// Competencias
	Component formulario;
	Competencia competencia;
	Button btnVer;
	ServicioCompetencia servicioCompetencia;
	ServicioEquipoJuego servicioEquipoJuego;

	/*
	 * 0->Evento no registrado, 1->Evento pendiente, 2->Evento cancelado,
	 * 3->Evento Finalizado
	 */
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
	

	public void onSelect$lsbxFiltro() {

		switch (lsbxFiltro.getSelectedIndex()) {

		case 1: { // Entrenamiento
			calendars.setReadonly(false);
			btnVer.setVisible(false);
			form = "Entrenamiento/Vistas/Auxiliar_Agenda.zul";
			break;
		}
		case 2: { // Competencia
		    calendars.setMold("default");
			calendars.setDays(7);
			calendars.setReadonly(false);
			btnVer.setVisible(true);
			break;
		}
		case 3: {// mantenimiento
			calendars.setReadonly(false);
			btnVer.setVisible(false);
			calendars.setMold("default");
			break;
		}
		case 4: {// Actividad Complementaria
			calendars.setReadonly(false);
			btnVer.setVisible(false);
			calendars.setMold("default");
			break;
		}
		
		default:
			btnVer.setVisible(false);
			break;
		}
	}

	public void onClick$btnVer() {

		Component catalogo = Executions.createComponents("/Competencias/Vistas/FrmSelectorCompetencia.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				competencia = (Competencia) formulario.getVariable("competencia", false);
				cargarJuegos();
			}
		});
	}

	public void cargarJuegos() {
		Set<Juego> juegos = competencia.getJuegos();
		EventosCalendario eventosCalendario = new EventosCalendario();
		for (Iterator i = juegos.iterator(); i.hasNext();) {
			Juego j = (Juego) i.next();
			Date fecha = j.getFecha();
			Date hora = j.getHoraInicio();
			List<EquipoJuego> equipos = ConvertirConjuntoALista(j.getEquipoJuegos());
		 	Date fechaI = new Date(fecha.getYear(), fecha.getMonth(),
					fecha.getDate(), hora.getHours(), hora.getMinutes());	
		 	Date fechaF = new Date(fecha.getYear(), fecha.getMonth(),
					fecha.getDate(), hora.getHours() + 2, hora.getMinutes());
		 	String equipo1 = equipos.get(0).getEquipoCompetencia().getEquipo().getNombre();
		    String divisa1 = equipos.get(0).getEquipoCompetencia().getEquipo().getDivisa().getNombre();
		    String equipo2 = equipos.get(1).getEquipoCompetencia().getEquipo().getNombre();
		    String divisa2 = equipos.get(1).getEquipoCompetencia().getEquipo().getDivisa().getNombre();
		    String cadena = equipo1 + "(" + divisa1 + ")" + " vs " + equipo2 + "(" + divisa2 + ")";
			SimpleCalendarEvent e = crearEvento(fechaI, fechaF,cadena, obtenerColor(j),obtenerColor(j), j);
			eventosCalendario.cargarEvento(e);
		}
		calendars.setModel(eventosCalendario.getModel());
	}
	
	public String obtenerColor(Juego j){
		 if (j.getDatoBasico().getNombre().equals("POR REALIZAR")){
			 //System.out.println("ejecutada");
			 return color[1];
		 }else if(j.getDatoBasico().getNombre().equals("CULMINADO")){
			 //System.out.println("culminada");
			 return color[3];
		 }else{
			 //System.out.println("suspendido");
			 return color[0];
		 }
	}
	
	public Date obtenerDuracion(Juego j,Categoria c){
	
		List<CategoriaCompetencia> ccs  = ConvertirConjuntoALista(j.getCompetencia().getCategoriaCompetencias());
		for (Iterator i = ccs.iterator();i.hasNext();){
			CategoriaCompetencia cc = (CategoriaCompetencia) i.next();
			if (cc.getCategoria().getNombre().equals(c.getNombre()))
				return cc.getDuracionHora();
		}
		return null;
	}
	
	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}

	public SimpleCalendarEvent crearEvento(Date fi, Date ff, 
		String contenido, String colorFondo, String colorEncabezado,
		Object valor) {
		EventoSimpleCalendario esc = new EventoSimpleCalendario();
		esc.setBeginDate(fi);
		esc.setEndDate(ff);
		esc.setContent(contenido);
		esc.setContentColor(colorFondo);
		esc.setHeaderColor(colorEncabezado);
		esc.setValue(valor);
		return esc;
	}



	public void onEventCreate$calendars(CalendarsEvent event) {

	}

	// Se invoca cuando se da click en el calendario para crear un evento nuevo
	public void onEventCreate$calendars(ForwardEvent event) {

		switch (lsbxFiltro.getSelectedIndex()) {

		case 1: { // Entrenamiento
			break;
		}
		case 2: { // Competencia
			break;
		}
		case 3: {// Mantenimiento
			Component c = Executions.createComponents(
					"Logistica/Vistas/frmPlanificarMantenimiento.zul", null,
					null);
			break;
		}
		case 4: {// Actividad Complementaria
			Component c = Executions.createComponents(
					"Logistica/Vistas/frmPlanificarActividad.zul", null, null);
			break;
		}
		}
	}

	public void onEventEdit$calendars(CalendarsEvent e) {
		EventoSimpleCalendario esc = (EventoSimpleCalendario) e.getCalendarEvent();		
		Window w = (Window) Executions.createComponents("/Competencias/Vistas/FrmAccionJuego.zul", null, null);
        w.setPosition("center");
        Juego j = (Juego) esc.getValue();
        w.setVariable("juego",j,false);
	}

	/*
	 * Programacion del toolbar del calendario
	 */
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
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
package controlador.general;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.Competencia;
import modelo.EquipoJuego;
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
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Span;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioCompetencia;
import servicio.implementacion.ServicioEquipoJuego;

public class CntrlFrmAgenda extends GenericForwardComposer {

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

	// Competencias
	Component formulario;
	Competencia competencia;
	Button btnVer;
	ServicioCompetencia servicioCompetencia;
	ServicioEquipoJuego servicioEquipoJuego;

	/*
	 * 0->por registrar, 1->registrado, 2->pendientes,
	 * 3->suspendidos
	 */
	String[] color = { "#32CD32", "#FDD017", "#ff7700", "#f13616" };

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
	

	public void onSelect$lsbxFiltro() {

		switch (lsbxFiltro.getSelectedIndex()) {

		case 1: { // Entrenamiento
			calendars.setReadonly(false);
			btnVer.setVisible(false);
			form = "Entrenamiento/Vistas/Auxiliar_Agenda.zul";
			break;
		}
		case 2: { // Competencia
		    calendars.setMold("default");
			calendars.setDays(7);
			calendars.setReadonly(false);
			btnVer.setVisible(true);
			break;
		}
		case 3: {// mantenimiento
			calendars.setReadonly(false);
			btnVer.setVisible(false);
			calendars.setMold("default");
			break;
		}
		case 4: {// Actividad Complementaria
			calendars.setReadonly(false);
			btnVer.setVisible(false);
			calendars.setMold("default");
			break;
		}
		
		default:
			btnVer.setVisible(false);
			break;
		}
	}

	public void onClick$btnVer() {

		Component catalogo = Executions.createComponents("/Competencias/Vistas/FrmSelectorCompetencia.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				competencia = (Competencia) formulario.getVariable("competencia", false);
				cargarJuegos();
			}
		});
	}

	public void cargarJuegos() {
		Set<Juego> juegos = competencia.getJuegos();
		EventosCalendario eventosCalendario = new EventosCalendario();
		for (Iterator i = juegos.iterator(); i.hasNext();) {
			Juego j = (Juego) i.next();
			Date fecha = j.getFecha();
			Date hora = j.getHoraInicio();
			List<EquipoJuego> equipos = ConvertirConjuntoALista(j.getEquipoJuegos());
		 	Date fechaI = new Date(fecha.getYear(), fecha.getMonth(),
					fecha.getDate(), hora.getHours(), hora.getMinutes());	
		 	Date fechaF = new Date(fecha.getYear(), fecha.getMonth(),
					fecha.getDate(), hora.getHours() + 2, hora.getMinutes());
		 	String equipo1 = equipos.get(0).getEquipoCompetencia().getEquipo().getNombre();
		    String divisa1 = equipos.get(0).getEquipoCompetencia().getEquipo().getDivisa().getNombre();
		    String equipo2 = equipos.get(1).getEquipoCompetencia().getEquipo().getNombre();
		    String divisa2 = equipos.get(1).getEquipoCompetencia().getEquipo().getDivisa().getNombre();
		    String cadena = equipo1 + "(" + divisa1 + ")" + " vs " + equipo2 + "(" + divisa2 + ")";
			SimpleCalendarEvent e = crearEvento(fechaI, fechaF,cadena, obtenerColor(j),obtenerColor(j), j);
			eventosCalendario.cargarEvento(e);
		}
		calendars.setModel(eventosCalendario.getModel());
	}
	
	public String obtenerColor(Juego j){
		 if (j.getDatoBasico().getNombre().equals("POR REALIZAR")){
			 //System.out.println("ejecutada");
			 return color[1];
		 }else if(j.getDatoBasico().getNombre().equals("CULMINADO")){
			 //System.out.println("culminada");
			 return color[3];
		 }else{
			 //System.out.println("suspendido");
			 return color[0];
		 }
	}
	
	public Date obtenerDuracion(Juego j,Categoria c){
	
		List<CategoriaCompetencia> ccs  = ConvertirConjuntoALista(j.getCompetencia().getCategoriaCompetencias());
		for (Iterator i = ccs.iterator();i.hasNext();){
			CategoriaCompetencia cc = (CategoriaCompetencia) i.next();
			if (cc.getCategoria().getNombre().equals(c.getNombre()))
				return cc.getDuracionHora();
		}
		return null;
	}
	
	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}

	public SimpleCalendarEvent crearEvento(Date fi, Date ff, 
		String contenido, String colorFondo, String colorEncabezado,
		Object valor) {
		EventoSimpleCalendario esc = new EventoSimpleCalendario();
		esc.setBeginDate(fi);
		esc.setEndDate(ff);
		esc.setContent(contenido);
		esc.setContentColor(colorFondo);
		esc.setHeaderColor(colorEncabezado);
		esc.setValue(valor);
		return esc;
	}



	public void onEventCreate$calendars(CalendarsEvent event) {

	}

	// Se invoca cuando se da click en el calendario para crear un evento nuevo
	public void onEventCreate$calendars(ForwardEvent event) {

		switch (lsbxFiltro.getSelectedIndex()) {

		case 1: { // Entrenamiento
			break;
		}
		case 2: { // Competencia
			break;
		}
		case 3: {// Mantenimiento
			Component c = Executions.createComponents(
					"Logistica/Vistas/frmPlanificarMantenimiento.zul", null,
					null);
			break;
		}
		case 4: {// Actividad Complementaria
			Component c = Executions.createComponents(
					"Logistica/Vistas/frmPlanificarActividad.zul", null, null);
			break;
		}
		}
	}

//	public void onEventEdit$calendars(CalendarsEvent e) {
//		EventoSimpleCalendario esc = (EventoSimpleCalendario) e.getCalendarEvent();		
//		Window w = (Window) Executions.createComponents("/Competencias/Vistas/FrmAccionJuego.zul", null, null);
//        w.setPosition("center");
//        Juego j = (Juego) esc.getValue();
//        w.setVariable("juego",j,false);
//	}

	/*
	 * Programacion del toolbar del calendario
	 */
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
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
}