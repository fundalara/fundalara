package servicio.interfaz;

import java.util.List;

import modelo.AfeccionJugador;

public interface IServicioAfeccionJugador {
public abstract void eliminar(AfeccionJugador c);
	
	public abstract void agregar(AfeccionJugador c);
		
	public abstract void actualizar(AfeccionJugador c);	
	
	public abstract List<AfeccionJugador> listar();
}
