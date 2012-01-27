package servicio.interfaz;

import java.util.List;

import modelo.RosterPlan;


public interface IServicioRosterPlan {
	public abstract void eliminar(RosterPlan a);

	public abstract void agregar(RosterPlan a);

	public abstract void actualizar(RosterPlan a);

	public abstract List<RosterPlan> listar();
}
