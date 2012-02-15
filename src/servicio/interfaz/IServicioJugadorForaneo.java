package servicio.interfaz;

import java.util.List;

import modelo.Equipo;
import modelo.JugadorForaneo;


public interface IServicioJugadorForaneo {
	
	public abstract void eliminar(JugadorForaneo j);

	public abstract void agregar(JugadorForaneo j);
	
	public abstract  List<JugadorForaneo> listar ();
	
	public abstract List<JugadorForaneo> listarActivos();
	
	public abstract List<JugadorForaneo> buscarJugadorForaneo(String ced);
	
	public abstract List<JugadorForaneo> listarJugadorForaneoPorCategoria(int codigo);
	
	public List<JugadorForaneo> listarJugadorForaneoPorFiltro(String dato);
}