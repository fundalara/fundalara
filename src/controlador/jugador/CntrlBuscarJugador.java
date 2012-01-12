package controlador.jugador;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import modelo.Categoria;

import modelo.Equipo;
import modelo.Jugador;
import modelo.Roster;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioRoster;

/**
 * Clase controladora de los eventos de la vista de igual nombre, el presente
 * controlador se encarga de buscar a un jugador dentro de un equipo devuelve al
 * catalogo que lo llamo el jugador seleccionado
 * 
 * @author Miguel B
 * 
 * @version 1.0 29/12/2011
 * 
 * */

public class CntrlBuscarJugador extends GenericForwardComposer {
	ServicioCategoria servicioCategoria;
	ServicioEquipo servicioEquipo;
	ServicioRoster servicioRoster;

	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();
	private Jugador Jugador = new Jugador();
	private Roster roster = new Roster();

	List<Jugador> Jugadores = new ArrayList<Jugador>();
	List<Equipo> listaEquipos = new ArrayList<Equipo>();
	List<Equipo> Equipos;

	Textbox filter2;
	Textbox filter1;
	Textbox filter3;
	Textbox filter4;
	Listbox listEquipo;

	Component catalogo;
	private AnnotateDataBinder binder;

	Combobox cmbEquipo, cmbCategoria;

	public void onChanging$filter2(){
		binder.loadAll();
		
	}
	
	public void onChanging$filter1(){
		binder.loadAll();
		
	}
	
	public void onChanging$filter3(){
		binder.loadAll();
		
	}
	
	public void onChanging$filter4(){
		binder.loadAll();
		
	}
	
	@Override
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("controller", this, true);
		// se guarda la referencia al catalogo
		catalogo = c;
		cmbEquipo.setDisabled(true);
		
	}

	public void onSelect$cmbCategoria() {
		cmbEquipo.setDisabled(false);
		cmbEquipo.getItems().clear();
		cmbEquipo.setValue("--Seleccione--");
	}

	
	public void onClick$btnSeleccionar() throws InterruptedException{
		//Se comprueba que se haya seleccionado un elemento de la lista

		if (listEquipo.getSelectedIndex() != -1) {
			//se obtiene la divisa seleccionada
			Jugador d = (Jugador) listEquipo.getSelectedItem().getValue();
			//se obtiene la referencia del formulario
			Component formulario = (Component) catalogo.getVariable("formulario",false);
	        //se le asigna el objeto divisa al formulario
			formulario.setVariable("jugador", d,false);
			//se le envia una señal al formulario indicado que el formulario se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoCerrado",formulario));          
			//se cierra el catalogo
			catalogo.detach();
			
		} else {
			Messagebox.show("Seleccione un Jugador", "Mensaje", Messagebox.YES,
					Messagebox.INFORMATION);

		}
		
		}

	
	
	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Jugador getJugador() {
		return Jugador;
	}

	public void setJugador(Jugador jugador) {
		Jugador = jugador;
	}

	public List<Equipo> getListaEquipos() {
		return listaEquipos;
	}

	public void setListaEquipos(List<Equipo> listaEquipos) {
		this.listaEquipos = listaEquipos;
	}

	public List<Categoria> getCategorias() {
		return servicioCategoria.listar();

	}

	public List<Equipo> getEquipos() {
		return servicioEquipo.buscarPorCategoria(categoria);

	}

	public List<Jugador> getJugadores() {
		return servicioRoster.buscarJugadores(equipo, filter2.getValue()
				.toString(), filter3.getValue().toString(), filter4.getValue()
				.toString(), filter1.getValue().toString());
	}

}
