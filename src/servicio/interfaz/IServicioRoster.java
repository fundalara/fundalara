package servicio.interfaz;

import java.util.List;

import modelo.Roster;

public interface IServicioRoster {
	public abstract void eliminar(Roster c);

	public abstract void agregar(Roster c);

	public abstract void actualizar(Roster c);

	public abstract List<Roster> listar();
}
