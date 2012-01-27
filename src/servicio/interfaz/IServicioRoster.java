package servicio.interfaz;

import java.util.List;

import modelo.Equipo;
import modelo.Jugador;
import modelo.Roster;

public interface IServicioRoster {
	
	public abstract void eliminar(Roster c);

	public abstract void agregar(Roster c);

	public abstract void actualizar(Roster c);

	public abstract List<Roster> listar();
	
	public List<Jugador> buscarJugadores(Equipo equipo, String filtro2, String filtro3, String filtro4, String filtro1);
	
	public abstract int obtenerUltimoId();
	
	public abstract Roster buscarRoster(String ced);
	
	public abstract List<Roster> listar(int codigo);

}
