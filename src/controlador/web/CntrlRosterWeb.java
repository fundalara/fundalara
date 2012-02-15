package controlador.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import modelo.Categoria;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Roster;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioRoster;

public class CntrlRosterWeb extends GenericForwardComposer {

	private ServicioRoster servicioRoster;
	private ServicioCategoria servicioCategoria;
	private ServicioEquipo servicioEquipo;

	// Modelos
	private Jugador jugador = new Jugador();
	private Roster roster = new Roster();
	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();

	// binder
	private AnnotateDataBinder binder;

	// id de la interfaz
	private Combobox cmbCategoria;
	private Combobox cmbEquipo;
	private Window winRosterWeb;
	private Listbox listJugadores;

	// lista
	List<Jugador> listaJug;

	@Override
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("controller", this, true);
		cmbEquipo.setDisabled(true);
	}

	// Getters y Setters
	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Roster getRoster() {
		return roster;
	}

	public void setRoster(Roster roster) {
		this.roster = roster;
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

	public void setRequestScope(Map<String, Object> requestScope) {
		this.requestScope = requestScope;
	}

	// Para llenar Listas y combos
	public List<Categoria> getCategorias() {
		return servicioCategoria.listar();
	}

	public List<Categoria> getCatAscenso() {
		return servicioCategoria.listar();
	}

	public List<Equipo> getEquipos() {
		return servicioEquipo.buscarPorCategoria(categoria);
	}

	public void onSelect$cmbEquipo() {
		if (cmbEquipo.getSelectedItem() != null) {
			equipo = (Equipo) cmbEquipo.getSelectedItem().getValue();
			if (equipo != null) {
				listaJug = servicioRoster.listarJugadores(equipo);
				binder.loadComponent(listJugadores);
			}
		}
	}

	public List<Jugador> getJugadores() {
		return listaJug;
	}

	// Metodos
	public void onSelect$cmbCategoria() {
		cmbEquipo.setDisabled(false);
		cmbEquipo.getItems().clear();
		cmbEquipo.setSelectedIndex(-1);
		listaJug= new ArrayList<Jugador>();
		binder.loadComponent(listJugadores);
		binder.loadAll();
		binder.loadComponent(cmbEquipo);
		binder.loadComponent(cmbCategoria);
	}
	
	public void limpiar() {
		cmbEquipo.setValue("--Seleccione--");
		cmbCategoria.setValue("--Seleccione--");
		cmbEquipo.setDisabled(true);
		equipo = new Equipo();
		categoria = new Categoria();
	}

}