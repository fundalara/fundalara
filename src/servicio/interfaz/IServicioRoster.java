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
	
	public abstract int obtenerUltimoId();
	
	public abstract Roster buscarRoster(String ced);
	
	public abstract List<Roster> listar(int codigo);
	
	public abstract List<Roster> listarxDivisaxCategoria(int codigocat, int codigoeq);

}
