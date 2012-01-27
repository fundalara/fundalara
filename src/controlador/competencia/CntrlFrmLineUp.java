package controlador.competencia;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Equipo;
import modelo.EquipoJuego;
import modelo.Juego;
import modelo.Jugador;
import modelo.LineUp;
import modelo.Roster;
import modelo.RosterCompetencia;

import org.jruby.RubyProcess.Sys;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioEquipoJuego;
import servicio.implementacion.ServicioJuego;
import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioLineUp;
import servicio.implementacion.ServicioRoster;

public class CntrlFrmLineUp extends GenericForwardComposer {
	
	Component formulario;
	AnnotateDataBinder binder;
	ServicioJuego servicioJuego;
	ServicioJugador servicioJugador;
	ServicioRoster servicioRoster;
	ServicioLineUp servicioLineUp;
	ServicioEquipoJuego servicioEquipoJuego;
	Equipo equipoA;
	Equipo equipoB;
	 
	Roster roster;
	Juego juego;
	LineUp lineUp;
	EquipoJuego equipoJuego;
	
	Combobox cmbPosicion;
	Textbox txtJuego;
	Textbox txtCategoria;
	
	List<Roster> jugadores;
	List<Roster> jugadoresSeleccionados;
	List<EquipoJuego> equipos;
	
	Listbox lsbxRoster;
	Listbox lsbxRosterSeleccionado;
	
	//Este metodo se llama al cargar la ventana
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		lineUp = new LineUp();
		//Se utiliza para hacer referencia a los objetos desde la vista (ej cntrl.algo). Debe ir siempre aqui
		c.setVariable("cntrl", this, true);
		
		jugadores = servicioRoster.listar();
		equipos = servicioEquipoJuego.listar();
		//jugadores = servicioRoster.listarEquipoPorJuego(juego);
		//se guarda la referencia al formulario actual ej (frmDivisa)	
		jugadoresSeleccionados = new ArrayList<Roster>();
		formulario = c;

		
		
	}
	

	
	
	public void onCreate$frmLineUp(){
		juego = (Juego) formulario.getVariable("juego",false);
		equipos = ConvertirConjuntoALista(juego.getEquipoJuegos());
		equipoA = equipos.get(0).getEquipoCompetencia().getEquipo();
		equipoB = equipos.get(1).getEquipoCompetencia().getEquipo();
		txtJuego.setText(equipoA.getNombre() + " vs " + equipoB.getNombre());
		txtCategoria.setText(equipoA.getCategoria().getNombre());
	
		binder.loadAll();
		
	}

	public void Quitar(Listbox origen, List lista) {
		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			Listitem li = (Listitem) i.next();
			Object o = li.getValue();
			lista.remove(o);
		}
	}
	
	public void Agregar(Listbox origen, Listbox destino, List lista) {

		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			boolean sw = false;
			Listitem li = (Listitem) i.next();
			Roster c1 = (Roster) li.getValue();
			List seleccionDestino = destino.getItems();
			for (Iterator j = seleccionDestino.iterator(); j.hasNext();) {
				Listitem li2 = (Listitem) j.next();
				Roster c2 = (Roster) li2.getValue();

				if (c1.getJugador().getCedulaRif().equals(c2.getJugador().getCedulaRif())) {
					sw = true;
					break;
				}
			}
			if (!sw) {
				lista.add(c1);
			}
		}
	}
	
	public void restaurar(){
		roster = new Roster();
		jugadores = new ArrayList<Roster>();
	}
	

	
	//Llama al catalogo
	public void onClick$btnBuscar(){
		Component catalogos = Executions.createComponents("/General/Calendario/agenda.zul", null, null);
		catalogos.setVariable("formulario", formulario, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {		
			@Override
			public void onEvent(Event arg0) throws Exception {
				juego = (Juego) formulario.getVariable("juego",false);
				binder.loadAll();				
			}
		});
	
	}
	
	// Convierte una conjunto a una lista...
	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}
	

	// Convierte una lista a un conjunto...
	public Set ConvertirListaAConjunto(List lista) {
		Set c = new HashSet();
		for (Iterator i = lista.iterator(); i.hasNext();) {
			c.add(i.next());
		}
		return c;
	}
	
	public void onClick$btnAgregar() {		
		Agregar(lsbxRoster,lsbxRosterSeleccionado,
				jugadoresSeleccionados);
		binder.loadAll();
	}
	
	public void onClick$btnQuitar(){
        Quitar(lsbxRosterSeleccionado,jugadoresSeleccionados);
		binder.loadAll();
	
	}
	
	//BOTONES GUARDAR, ELIMINAR, CANCELAR Y SALIR
	
	public void onClick$btnGuardar() throws InterruptedException{
			if (txtJuego.getText() != null)
				if (lsbxRosterSeleccionado.getItems().size() > 0) {
					roster.setRosterCompetencias(ConvertirListaAConjunto(jugadoresSeleccionados));
					servicioRoster.agregar(roster);
					Messagebox.show("Datos agregados exitosamente", "Mensaje",
							Messagebox.OK, Messagebox.EXCLAMATION);
					restaurar();
					binder.loadAll();

				} else
					Messagebox.show("Seleccione la Categoria", "Mensaje",
							Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	/*public void onClick$btnEliminar() throws InterruptedException{
		if(Messagebox.show("�Realmente desea eliminar esta constante","Mensaje",Messagebox.YES+Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES){
			
			servicioRoster.eliminar(roster);
			roster = new Roster();
			binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
			}
		}*/
	
	public void onClick$btnCancelar(){
		restaurar();
		binder.loadAll();
	}
	
	public void onClick$btnSalir(){
		formulario.detach();
		
	}

	
	// GETTER AND SETTER
	public List<Roster> getJugadores() {
		return jugadores;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public void setJugadores(List<Roster> jugadores) {
		this.jugadores = jugadores;
	}

	public List<Roster> getJugadoresSeleccionados() {
		return jugadoresSeleccionados;
	}

	public void setJugadoresSeleccionados(List<Roster> jugadoresSeleccionados) {
		this.jugadoresSeleccionados = jugadoresSeleccionados;
	}

	public Listbox getLsbxRoster() {
		return lsbxRoster;
	}

	public void setLsbxRoster(Listbox lsbxRoster) {
		this.lsbxRoster = lsbxRoster;
	}

	public Listbox getLsbxRosterSeleccionado() {
		return lsbxRosterSeleccionado;
	}

	public void setLsbxRosterSeleccionado(Listbox lsbxRosterSeleccionado) {
		this.lsbxRosterSeleccionado = lsbxRosterSeleccionado;
	}

	public EquipoJuego getEquipoJuego() {
		return equipoJuego;
	}

	public void setEquipoJuego(EquipoJuego equipoJuego) {
		this.equipoJuego = equipoJuego;
	}

	public List<EquipoJuego> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<EquipoJuego> equipos) {
		this.equipos = equipos;
	}

	public LineUp getLineUp() {
		return lineUp;
	}

	public void setLineUp(LineUp lineUp) {
		this.lineUp = lineUp;
	}

}
