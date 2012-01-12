package servicio.interfaz;

import java.util.List;

import modelo.DatoBasico;
import modelo.Jugador;
import modelo.TallaPorJugador;

/**
 * Interfaz  que define el contrato de los servicios  para el acceso y manejo de los datos  de las tallas de los jugadores 
 * @author Robert A
 * @author German L
 * @version 0.1 30/12/2011
 *
 */
public interface IServicioTallaPorJugador {
	
	public abstract void eliminar(TallaPorJugador c);

	public abstract void agregar(TallaPorJugador c);

	public abstract void actualizar(TallaPorJugador c);

	public abstract List<TallaPorJugador> listar();
	
	public abstract void agregar(Jugador jugador, DatoBasico... tallas );
	
}

