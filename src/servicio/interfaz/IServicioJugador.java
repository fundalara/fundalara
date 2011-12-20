package servicio.interfaz;

import java.util.List;

import modelo.Jugador;

public interface IServicioJugador {
public abstract void eliminar(Jugador c);
	
	public abstract void agregar(Jugador c);
		
	public abstract void actualizar(Jugador c);	
	
	public abstract List<Jugador> listar();
}
