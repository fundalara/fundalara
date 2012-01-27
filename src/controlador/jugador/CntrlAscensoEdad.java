package controlador.jugador;

import java.util.Date;
import java.util.List;
import java.util.Map;

import modelo.Ascenso;
import modelo.Categoria;
import modelo.DatoBasico;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Roster;

import org.python.antlr.PythonParser.return_stmt_return;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Window;

import comun.Util;

import servicio.implementacion.ServicioAscenso;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioRoster;

/**
 * Clase controladora de los eventos de la vista de igual nombre y manejo de los
 * servicios de datos para el ascenso por edad de los jugadores
 * */
public class CntrlAscensoEdad extends GenericForwardComposer {

	// Servicios
	private ServicioRoster servicioRoster;
	private ServicioAscenso servicioAscenso;
	private ServicioJugador servicioJugador;
	private ServicioCategoria servicioCategoria;
	private ServicioEquipo servicioEquipo;

	// Modelos
	private Jugador jugador = new Jugador();
	private Roster roster = new Roster();
	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();
	private Ascenso ascenso = new Ascenso();
	private DatoBasico tipoAscenso = new DatoBasico();

	// binder
	private AnnotateDataBinder binder;

	private Map<String, Object> requestScope;

	// id de la interfaz
	private Combobox cmbCategoria;
	private Combobox cmbEquipo;
	private Combobox cmbEquipoAsc;
	private Combobox cmbCategoriaAsc;
	private Window winAscensoEdad;
	
	//lista
	List<Jugador> listaJug;

	@Override
	// Coloca visible el modelo para el databinder
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, true);
		this.tipoAscenso = (DatoBasico) requestScope.get("tipoAscenso");
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

	public Ascenso getAscenso() {
		return ascenso;
	}

	public void setAscenso(Ascenso ascenso) {
		this.ascenso = ascenso;
	}

	public Map<String, Object> getRequestScope() {
		return requestScope;
	}

	public void setRequestScope(Map<String, Object> requestScope) {
		this.requestScope = requestScope;
	}

	// Para llenar los combos
	
	public List<Categoria> getCategorias() {
		return servicioCategoria.listar();
	}
	
	public List<Categoria> getCatAscenso() {
		return servicioCategoria.listar();
	}

	public List<Equipo> getEquipos() {
		return servicioEquipo.buscarPorCategoria(categoria);
	}

	public List<Jugador> getJugadores() {
		listaJug= servicioRoster.buscarJugadores(equipo, "", "", "", "");
       return listaJug;
	}

	// Eventos	
	
	public void onSelect$cmbCategoria() {
		cmbEquipo.setDisabled(false);
	}
	
	public void onSelect$cmbEquipo() {
		cmbEquipoAsc.setDisabled(false);
		cmbCategoriaAsc.setDisabled(false);		
	}

	public void onClick$btnGuardar() {
	
	}

	public void onClick$btnCancelar() {
		limpiar();
	}

	public void onClick$btnSalir() {
		winAscensoEdad.detach();
	}
	
	//Métodos propios del controlador
	
	public void limpiar(){
		cmbEquipo.setValue(null);
		cmbCategoria.setValue(null);
		cmbEquipo.setDisabled(true);
		cmbEquipoAsc.setValue(null);
		cmbCategoriaAsc.setValue(null);	
		equipo=new Equipo();
		categoria=new Categoria();
		binder.loadAll();
	}	

}
