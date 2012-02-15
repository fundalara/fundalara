package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioJugador;

import dao.general.DaoJugador;
import dao.general.DaoPersona;
import dao.general.DaoPersonaNatural;
import modelo.Jugador;
import modelo.PersonaNatural;

/**
 * Clase para brindar los servicios para manejar los datos relacionados con los jugadores
 * @author Robert A
 * @author German L
 * @version 0.1 29/12/2011
 *
 */
public class ServicioJugador implements IServicioJugador {
    
	DaoJugador daoJugador;
	
	public DaoJugador getDaoJugador() {
		return daoJugador;
	}

	public void setDaoJugador(DaoJugador daoJugador) {
		this.daoJugador = daoJugador;
	}


	
	///
	
	
	@Override
	public void eliminar(Jugador c) {
		daoJugador.eliminar(c);
	}

	@Override
	public void agregar(Jugador c) {
		daoJugador.guardar(c);
	}

	@Override
	public void actualizar(Jugador c) {
		 daoJugador.actualizar(c);
	}
	

	@Override
	public List<Jugador> listar() {
		return daoJugador.listar( Jugador.class);
	}
	
	public void retirarJugador(Jugador jugador) {
		daoJugador.retirar(jugador);
	}
	
	public List<Jugador> buscarJugadores(String filtro2, String filtro3, String filtro4, String filtro1, char estatus){
		return daoJugador.buscarJugadores(filtro2, filtro3, filtro4, filtro1, estatus);
	}
	
	public int generarCodigoTemporal(){
		return daoJugador.generarCodigoTemporal();
	}

	public String actualizarDatosJugador(Jugador jugador) {
		return daoJugador.actualizarDatosJugador(jugador);
	}
	
	
	public void cambiarCedula(String nuevaCedula, Jugador jugador) {
		 daoJugador.cambiarCedula(nuevaCedula, jugador);
	}
}
