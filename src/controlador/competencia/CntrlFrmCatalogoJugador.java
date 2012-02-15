package controlador.competencia;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.Jugador;
import modelo.JugadorForaneo;
import modelo.PersonaNatural;
import modelo.Roster;
import modelo.RosterCompetencia;
import modelo.Competencia;
import modelo.Equipo;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioJugadorForaneo;
import servicio.implementacion.ServicioRoster;
import servicio.implementacion.ServicioRosterCompetencia;

public class CntrlFrmCatalogoJugador extends GenericForwardComposer {
	
	AnnotateDataBinder binder;
	
	
	int codEq;
	Listbox lsbxJugador;
	Jugador jugador;
	ServicioJugador servicioJugador;
	ServicioRoster servicioRoster;
	List<Roster> jugadoresRoster;
	Component catalogo;
	Textbox txtFiltro;
	Roster roster;
	PersonaNatural personaNatural;
	List<RosterCompetencia> jugadoresxRoster;
	List<Roster> jugadores;
	
	List<RosterCompetencia> jugadoresCompitiendo;
	ServicioRosterCompetencia servicioRosterCompetencia;
	
	Competencia competencia;
	Equipo equipo;
	int codCat;
	int codcomp;
	
	
	
	
	
	
	
	public Jugador getJugador() {
		return jugador;
	}




	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}




	public List<Roster> getJugadores() {
		return jugadores;
	}




	public void setJugadores(List<Roster> jugadores) {
		this.jugadores = jugadores;
	}




	public List<RosterCompetencia> getJugadoresCompitiendo() {
		return jugadoresCompitiendo;
	}




	public void setJugadoresCompitiendo(List<RosterCompetencia> jugadoresCompitiendo) {
		this.jugadoresCompitiendo = jugadoresCompitiendo;
	}




	public List<Roster> getJugadoresRoster() {
		return jugadoresRoster;
	}




	public void setJugadoresRoster(List<Roster> jugadoresRoster) {
		this.jugadoresRoster = jugadoresRoster;
	}




	public void onCreate$frmCatalogoJugador(){
		
		
		
		codEq = (Integer) catalogo.getVariable("codigoEq",false);
		codCat = (Integer) catalogo.getVariable("codigoCat",false);
		codcomp = (Integer) catalogo.getVariable("codigocomp", false);
		competencia = (Competencia) catalogo.getVariable("competencia", false);
		equipo = (Equipo) catalogo.getVariable("equipo", false);
		jugadoresxRoster =  (List<RosterCompetencia>) catalogo.getVariable("ljugadores", false);
		System.out.print("RECIBO COD EQUIPO=");
		System.out.println(codEq);
		System.out.print("RECIBO COD CATEGORIA=");
		System.out.println(codCat);
		System.out.print("Jugadores Recibidos=");
		System.out.println(jugadoresxRoster.size());
		jugadoresRoster = servicioRoster.listarxDivisaxCategoria(codCat,codEq);
		jugadoresCompitiendo = servicioRosterCompetencia.listarJexistentes(codcomp);
		jugadores = jugadoressinCompetencia(jugadoresRoster, jugadoresCompitiendo);
		
		System.out.print("JUGADORES=");
		System.out.println(jugadores.size());
		
		
		
		//jugadorForaneo = servicioJugadorForaneo.listarJugadorForaneoPorCategoria(codCat);
		
		binder.loadAll();
	}
	
	
	
	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
			
		catalogo = c;
		roster = new Roster();
		personaNatural = new PersonaNatural();
		
		
		
		
		
		//si selecciona por defecto el primero de la lista si hay al menos 1
		if (lsbxJugador.getItems().size() != 0){
			lsbxJugador.setSelectedIndex(0);
		}

	}

	public void onClick$btnAceptar() throws InterruptedException {
	
		if (lsbxJugador.getSelectedIndex() != -1) {
		
					
				
				Roster ros = jugadores.get(lsbxJugador.getSelectedIndex());
				RosterCompetencia roscomp = new RosterCompetencia();
				roscomp.setRoster(ros);
				roscomp.setCompetencia(competencia);
				roscomp.setEquipo(equipo);
				//rosterCompetenciaNuevas.add(roscomp);
				System.out.println(roscomp.getRoster().getJugador().getPersonaNatural().getPrimerNombre());
				
				
			
			
			
			
			String cedJugadorNuevo = roscomp.getRoster().getJugador().getCedulaRif();
			
			if (!ExisteJugador(cedJugadorNuevo)){
			
				Component formulario = (Component) catalogo.getVariable("formulario",false);
				
				formulario.setVariable("r",roscomp,false);
				System.out.println(roscomp.getRoster().getJugador().getPersonaNatural().getPrimerNombre());
	            Events.sendEvent(new Event("onCatalogoCerrado3",formulario));
	            catalogo.detach();
				
				
			}else{
				Messagebox.show("Ya existe este Jugador", "Olimpo - Información",	Messagebox.YES, Messagebox.INFORMATION);
			}
			
		} else {
				Messagebox.show("Seleccione un jugador", "Olimpo - Información",	Messagebox.YES, Messagebox.INFORMATION);

		}

	}
   
	public boolean ExisteJugador(String cedula){
		boolean k = false;
		
	for (Iterator i = jugadoresxRoster.iterator(); i.hasNext(); ){
			
			
			RosterCompetencia Rjugador = (RosterCompetencia) i.next();
			String AuxcedJugador = Rjugador.getRoster().getJugador().getPersonaNatural().getCedulaRif();
			
				
					if (AuxcedJugador==cedula){
						System.out.println("ENTRO AL IF");
						k = true;
						
					}
				
				
			}
			
			
		return k;
	}	
	
	
	public List<Roster> jugadoressinCompetencia(List<Roster> lista1, List<RosterCompetencia> lista2){
		
		List<Roster> auxlist = new ArrayList<Roster>();
		boolean encuentro=false;
		
		for(Iterator i = lista1.iterator(); i.hasNext(); ){
			
			Roster Rjugador = (Roster) i.next();
			String AuxcedJugador = Rjugador.getJugador().getCedulaRif();
			System.out.print("Cedula De jugador=");
			System.out.println(AuxcedJugador);
			
			
		
			for(Iterator j = lista2.iterator(); j.hasNext(); ){
				
				RosterCompetencia Rcjugador = (RosterCompetencia) j.next();
				if (Rcjugador.getRoster()!=null){
				String AuxcedJugadorCompetencia = Rcjugador.getRoster().getJugador().getCedulaRif();
				
				System.out.print("Cedula De jugador en competencia=");
				System.out.println(AuxcedJugadorCompetencia);	
				
				
				if (AuxcedJugadorCompetencia!=AuxcedJugador){
					encuentro= true;
					
					
				}}
				
				
				
			}
			if (encuentro){
				
				auxlist.add(Rjugador);
			}
			
	}
		return auxlist;

}
}