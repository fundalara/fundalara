package controlador.general;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Competencia;
import modelo.EquipoJuego;
import modelo.Juego;

import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.calendar.impl.SimpleCalendarEvent;
import org.zkoss.zhtml.Span;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import comun.EstadoCompetencia;

import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioCompetencia;


public class CntrlFrmAgendaCompetencia extends CntrlFrmAgendaGeneral {
    
	
	Combobox cmbCompetencia;
	Button btnVer;
	Datebox dbxFechaI;
	Datebox dbxFechaF;
	Grid grid;
	Label lblCompetencia;
	Label lblFechaI;
	Label lblFechaF;
	
	Competencia competencia;
	List<Competencia> competencias;
	ServicioCompetencia servicioCompetencia;
	ServicioCategoriaCompetencia servicioCategoriaCompetencia;
	
	
	@Override
	public void doAfterCompose(Component component) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(component);

		// configurando visualizacion
		calendars.setMold("default");
		calendars.setDays(7);
		calendars.setReadonly(false);
        
		// Creando componentes necesarios
		hoy.setVisible(false);
<<<<<<< HEAD
		cmbCompetencia = new Combobox();
		cmbCompetencia.setCols(30);
		cmbCompetencia.setText("                     --Seleccione--");
=======
<<<<<<< HEAD
		cmbCompetencia = new Combobox();
		cmbCompetencia.setCols(30);
		cmbCompetencia.setText("                     --Seleccione--");
=======
<<<<<<< HEAD
		cmbCompetencia = new Combobox();
		cmbCompetencia.setCols(30);
		cmbCompetencia.setText("                     --Seleccione--");
=======
		lblCompetencia = new Label("Competencia ");
		cmbCompetencia = new Combobox();
		cmbCompetencia.setCols(24);
		cmbCompetencia.setText("- Seleccione una Competecia -");
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
		cmbCompetencia.setReadonly(true);
		cmbCompetencia.addForward(Events.ON_SELECT,formulario,"onSelecciono");
		btnVer = new Button();
		btnVer.setMold("os");
		btnVer.setImage("Recursos/Imagenes/consultar.ico");
		btnVer.addForward(Events.ON_CLICK,formulario,"onBuscar");
		lblFechaI = new Label("Fecha Inicio ");
		lblFechaI.setStyle("margin-left:5px");
		dbxFechaI = new Datebox();
		dbxFechaI.setCols(10);
		dbxFechaI.setReadonly(true);
		lblFechaF = new Label("Fecha Fin ");
		lblFechaF.setStyle("margin-left:5px");
		dbxFechaF = new Datebox();
		dbxFechaF.setCols(10);
		dbxFechaF.setReadonly(true);
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
		spnControles.appendChild(lblCompetencia);
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
		spnControles.appendChild(cmbCompetencia);
		spnControles.appendChild(btnVer);
	    spnControles.appendChild(lblFechaI);
	    spnControles.appendChild(dbxFechaI);
	    spnControles.appendChild(lblFechaF);
	    spnControles.appendChild(dbxFechaF);
	    cargarComboCompetencias();
	}
	
	public void onSelecciono(){
		competencia = (Competencia) cmbCompetencia.getSelectedItem().getValue();
		dbxFechaI.setValue(competencia.getFechaInicio());
		dbxFechaF.setValue(competencia.getFechaFin());
		cargarJuegos();
	}
	
	
	public void cargarComboCompetencias(){
		competencias = servicioCompetencia.listarPorEstatus(EstadoCompetencia.APERTURADA);
		for (int i=0;i<competencias.size();i++){
			Comboitem ci = new Comboitem();
			ci.setValue(competencias.get(i));
			ci.setLabel(competencias.get(i).getNombre());
			cmbCompetencia.appendChild(ci);
		}
		
	}
	
	
	public void onBuscar(){
		Component catalogo = Executions.createComponents("/Competencias/Vistas/FrmSelectorCompetencia.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				competencia = (Competencia) formulario.getVariable("competencia", false);
				cmbCompetencia.setSelectedIndex(buscarIndice(competencia));
				Events.postEvent(new Event(Events.ON_SELECT,cmbCompetencia));
			}
		});
	}
	
	public void onEventEdit$calendars(CalendarsEvent e) {
		EventoSimpleCalendario esc = (EventoSimpleCalendario) e.getCalendarEvent();		
<<<<<<< HEAD
		Window w = (Window) Executions.createComponents("/Competencias/Vistas/FrmRegistroResultados.zul", null, null);
=======
<<<<<<< HEAD
		Window w = (Window) Executions.createComponents("/Competencias/Vistas/FrmRegistroResultados.zul", null, null);
=======
<<<<<<< HEAD
		Window w = (Window) Executions.createComponents("/Competencias/Vistas/FrmRegistroResultados.zul", null, null);
=======
		Window w = (Window) Executions.createComponents("/Competencias/Vistas/FrmAccionJuego.zul", null, null);
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
        w.setPosition("center");
        Juego j = (Juego) esc.getValue();
        w.setVariable("juego",j,false);
        w.doHighlighted();
	}
	
	public int buscarIndice(Competencia c){
		for (int i=0;i<competencias.size();i++){
			if (c.getCodigoCompetencia() == competencias.get(i).getCodigoCompetencia()){
				return i;
			}
		}
		return 0;
	}
	
	public void cargarJuegos() {
		Set<Juego> juegos = competencia.getJuegos();
		EventosCalendario eventosCalendario = new EventosCalendario();
		for (Iterator i = juegos.iterator(); i.hasNext();) {
			Juego j = (Juego) i.next();
			Date fecha = j.getFecha();
			Date hora = j.getHoraInicio();
			List<EquipoJuego> equipos = ConvertirConjuntoALista(j.getEquipoJuegos());		
			//Obtiene la duraccion de los eventos de acuerdo a la categoria de los equipos
			Date duracionA = servicioCategoriaCompetencia.getDuraccionCategoriaHora(equipos.get(0).getEquipoCompetencia().getEquipo().getCategoria());
			Date duracionB = servicioCategoriaCompetencia.getDuraccionCategoriaHora(equipos.get(1).getEquipoCompetencia().getEquipo().getCategoria());	
			Date mayor;
			//Si las duraciones son diferente se deja la mayor
			if (duracionA.before(duracionB)){
		 		mayor = duracionB; 
			}else{
				mayor = duracionA;
			}
		 	Date fechaI = new Date(fecha.getYear(), fecha.getMonth(),fecha.getDate(), hora.getHours(), hora.getMinutes());	
		 	Date fechaF = new Date(fecha.getYear(), fecha.getMonth(),fecha.getDate(), hora.getHours() + mayor.getHours(), hora.getMinutes()+mayor.getMinutes());
		 	
			String equipo1 = equipos.get(0).getEquipoCompetencia().getEquipo().getNombre();
		    String divisa1 = equipos.get(0).getEquipoCompetencia().getEquipo().getDivisa().getNombre();
		    String equipo2 = equipos.get(1).getEquipoCompetencia().getEquipo().getNombre();
		    String divisa2 = equipos.get(1).getEquipoCompetencia().getEquipo().getDivisa().getNombre();
		    String cadena = equipo1 + "(" + divisa1 + ")" + " vs " + equipo2 + "(" + divisa2 + ")";
			SimpleCalendarEvent e = crearEvento(fechaI, fechaF,cadena, obtenerColor(j.getDatoBasico().getNombre()), j);
			eventosCalendario.cargarEvento(e);
		}
		calendars.setModel(eventosCalendario.getModel());
	}
	
	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}
	

}
