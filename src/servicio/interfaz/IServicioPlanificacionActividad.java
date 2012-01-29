package servicio.interfaz;

import java.util.List;

import modelo.PlanificacionActividad;

public interface IServicioPlanificacionActividad {

	public abstract void eliminar(PlanificacionActividad pa);

	public abstract void agregar(PlanificacionActividad pa);

	public abstract void actualizar(PlanificacionActividad pa);

	public abstract List<PlanificacionActividad> buscarVigente();

	public abstract List<PlanificacionActividad> listarPlantilla();

	public abstract List<PlanificacionActividad> listarActivos();

	public abstract List<PlanificacionActividad> listarMantenimientos();

	public abstract List<PlanificacionActividad> listarComplementarias();
	
	public abstract List<PlanificacionActividad> listar();
}
