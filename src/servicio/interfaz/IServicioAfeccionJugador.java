package servicio.interfaz;

import java.util.List;

import modelo.AfeccionJugador;

/**
 * Interfaz que define el contrato de los servicios para el acceso y manejo de
 * los datos  de las afecciones de los jugadores
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 30/12/2011
 * 
 */
public interface IServicioAfeccionJugador {

	public abstract void eliminar(AfeccionJugador c);
	
	public abstract void agregar(AfeccionJugador c);
		
	public abstract void actualizar(AfeccionJugador c);	
	
	public abstract List<AfeccionJugador> listar();
}
