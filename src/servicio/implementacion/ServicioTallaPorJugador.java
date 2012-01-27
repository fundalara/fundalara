package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioTallaPorJugador;

import dao.general.DaoTallaPorJugador;

import modelo.DatoBasico;
import modelo.Jugador;
import modelo.TallaPorJugador;

/**
 * Clase para brindar los servicios para manejar los datos relacionados con los
 * datos de las tallas de las indumentarias de los jugadores
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 01/01/2012
 * 
 */
public class ServicioTallaPorJugador implements IServicioTallaPorJugador {

	DaoTallaPorJugador daoTallaPorJugador;
	
	public DaoTallaPorJugador getDaoTallaPorJugador() {
		return daoTallaPorJugador;
	}

	public void setDaoTallaPorJugador(DaoTallaPorJugador daoTallaPorJugador) {
		this.daoTallaPorJugador = daoTallaPorJugador;
	}

	@Override
	public void eliminar(TallaPorJugador c) {
		daoTallaPorJugador.eliminar(c);

	}

	@Override
	public void agregar(TallaPorJugador c) {
		daoTallaPorJugador.guardar(c);

	}

	@Override
	public void actualizar(TallaPorJugador c) {
		daoTallaPorJugador.actualizar(c);

	}

	@Override
	public List<TallaPorJugador> listar() {
		return daoTallaPorJugador.listar( TallaPorJugador.class);
	}

	@Override
	public void agregar(Jugador jugador, DatoBasico... tallas) {
		daoTallaPorJugador.guardar(jugador, tallas);
	}

}
