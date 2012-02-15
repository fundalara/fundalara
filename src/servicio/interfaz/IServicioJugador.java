package servicio.interfaz;

import java.util.List;

import modelo.Jugador;
import modelo.PersonaNatural;

public interface IServicioJugador {
	
	public abstract void eliminar(Jugador c);
	
		
	public abstract void actualizar(Jugador c);	
	
	public abstract List<Jugador> listar();

	void agregar(Jugador c);
}
