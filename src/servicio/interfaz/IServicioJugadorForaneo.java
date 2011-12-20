package servicio.interfaz;

import java.util.List;

import modelo.JugadorForaneo;

public interface IServicioJugadorForaneo {
	
	public abstract void eliminar(JugadorForaneo j);

	public abstract void agregar(JugadorForaneo j);
	
	public abstract  List<JugadorForaneo> listar ();
	
	public abstract List<JugadorForaneo> listarActivos();

}
