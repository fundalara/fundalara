package controlador.jugador;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import modelo.Categoria;

import modelo.Equipo;
import modelo.Jugador;
import modelo.JugadorPlan;
import modelo.Roster;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import comun.EstatusRegistro;
import comun.Mensaje;

import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioJugadorPlan;

/**
 * Clase controladora de los eventos de la vista de igual nombre, el presente
 * controlador se encarga de buscar a un jugador dentro de un equipo devuelve al
 * catalogo que lo llamo el jugador seleccionado
 * 
 * @author Miguel B
 * @author Robert A
 * 
 * @version 1.0 29/12/2011
 * 
 * */
public class CntrlBuscarJugador extends GenericForwardComposer {
	private ServicioJugador servicioJugador;
	private ServicioJugadorPlan servicioJugadorPlan;

	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();
	private Jugador Jugador = new Jugador();
	private JugadorPlan jugadorPlan = new JugadorPlan();
	private Roster roster = new Roster();

	private List<Jugador> Jugadores = new ArrayList<Jugador>();
	private List<Equipo> listaEquipos = new ArrayList<Equipo>();
	private List<Equipo> Equipos;
	private List<JugadorPlan> jugadoresPlan = new ArrayList<JugadorPlan>();

	private Textbox filter2;
	private Textbox filter1;
	private Textbox filter3;
	private Textbox filter4;
	private Textbox filtroCedula;
	private Textbox filtroNombre;
	private Textbox filtroApellido;
	private Listbox listEquipo;
	private Listbox listJugadorPlan;
	private Window winBuscarjugador;

	private Component catalogo;
	private AnnotateDataBinder binder;
	private char estatus;

	private void filtrarLista() {
		Jugadores = servicioJugador.buscarJugadores(filter2.getValue()
				.toString().toUpperCase(), filter3.getValue().toString()
				.toUpperCase(), filter4.getValue().toString().toUpperCase(),
				filter1.getValue().toString().toUpperCase(), estatus);
		binder.loadComponent(listEquipo);
	}

	public void onBlur$filter2() {
		filtrarLista();
	}

	public void onBlur$filter1() {
		filtrarLista();
	}

	public void onBlur$filter3() {
		filtrarLista();
	}

	public void onBlur$filter4() {
		filtrarLista();
	}

	public void onBlur$filtroCedula() {
		filtrarListaPlan();
	}

	public void onBlur$filtroNombre() {
		filtrarListaPlan();
	}

	public void onBlur$filtroApellido() {
		filtrarListaPlan();
	}

	public void onCreate$winBuscarjugador() {
		estatus = (Character) catalogo.getVariable("estatus", false);
		determinarTitulo(estatus);
		if (estatus != EstatusRegistro.PLAN_VACACIONAL) {
			Jugadores = servicioJugador.buscarJugadores(filter2.getValue()
					.toString().toUpperCase(), filter3.getValue().toString()
					.toUpperCase(),
					filter4.getValue().toString().toUpperCase(), filter1
							.getValue().toString().toUpperCase(), estatus);
			binder.loadComponent(listEquipo);
		} else {
			filtrarListaPlan();
			listEquipo.setVisible(false);
			listJugadorPlan.setVisible(true);
		}
	}

	private void filtrarListaPlan() {
		jugadoresPlan = servicioJugadorPlan.buscarJugadores(filtroCedula
				.getValue().toString().toUpperCase(), filtroNombre.getValue()
				.toString().toUpperCase(), filtroApellido.getValue().toString()
				.toUpperCase());
		binder.loadComponent(listJugadorPlan);
	}

	public void determinarTitulo(char estatus) {
		Window w = (Window) catalogo;
		switch (estatus) {

		case 'A':
			w.setTitle("Catálogo Jugadores");
			break;
		case 'E':
			w.setTitle("Catálogo Reingreso");
			break;
		case 'T':
			w.setTitle("Catálogo Nuevo Ingreso");
			break;
		case 'R':
			w.setTitle("Catálogo Jugadores Regulares");
			break;
		case 'P':
			w.setTitle("Catálogo Jugadores Plan Vacacional");
			break;
		}
	}

	@Override
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("controller", this, true);
		catalogo = c;
	}

	public void onClick$btnSeleccionar() {
		if (listEquipo.isVisible()) {
			if (listEquipo.getSelectedIndex() != -1) {
				Jugador d = (Jugador) listEquipo.getSelectedItem().getValue();
				Component formulario = (Component) catalogo.getVariable(
						"formulario", false);
				formulario.setVariable("jugador", d, false);
				Events.sendEvent(new Event("onCatalogoBuscarJugadorCerrado",
						formulario));
				catalogo.detach();
			} else {
				Mensaje.mostrarMensaje("Seleccione un Jugador", "",
						Messagebox.INFORMATION);
			}
		} else {
			if (listJugadorPlan.getSelectedIndex() != -1) {
				Component formulario = (Component) catalogo.getVariable(
						"formulario", false);
				formulario.setVariable("jugadorPlan",  (JugadorPlan) listJugadorPlan.getSelectedItem().getValue(), false);
				Events.sendEvent(new Event("onCatalogoBuscarJugadorCerrado",
						formulario));
				catalogo.detach();
			} else {
				Mensaje.mostrarMensaje("Seleccione un Jugador", "",
						Messagebox.INFORMATION);
			}
		}
	}
	
	public void onClick$btnSalir(){
		winBuscarjugador.detach();
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

	public List<Jugador> getJugadores() {
		return Jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		Jugadores = jugadores;
	}

	public List<JugadorPlan> getJugadoresPlan() {
		return jugadoresPlan;
	}

	public JugadorPlan getJugadorPlan() {
		return jugadorPlan;
	}

	public void setJugadorPlan(JugadorPlan jugadorPlan) {
		this.jugadorPlan = jugadorPlan;
	}

}