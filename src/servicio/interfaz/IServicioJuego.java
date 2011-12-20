package servicio.interfaz;

import java.util.List;

import modelo.Juego;

public interface IServicioJuego {
	
	public abstract void eliminar(Juego j);

	public abstract void agregar(Juego j);
	
	public abstract  List<Juego> listar ();
	
	public abstract List<Juego> listarActivos();

}
