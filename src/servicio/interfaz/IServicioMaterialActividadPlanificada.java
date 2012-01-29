package servicio.interfaz;

import java.util.List;

import modelo.MaterialActividadPlanificada;
import modelo.PlanificacionActividad;

public interface IServicioMaterialActividadPlanificada {
	
	public abstract void eliminar(MaterialActividadPlanificada map);
	
	public abstract void agregar(MaterialActividadPlanificada map);
		
	public abstract void actualizar(MaterialActividadPlanificada map);

	public abstract List<MaterialActividadPlanificada> listar();

	public abstract MaterialActividadPlanificada buscarParaCantidadNecesaria(
			MaterialActividadPlanificada m);

	public abstract List<MaterialActividadPlanificada> listarPorPrestar(
			PlanificacionActividad a);

	public abstract List<MaterialActividadPlanificada> listarPorPrestarCompetencia(
			PlanificacionActividad a);

	public abstract List<MaterialActividadPlanificada> listarPorPrestarEvento(
			PlanificacionActividad a);

	public abstract List<MaterialActividadPlanificada> listarPorPrestarEntrenamiento(
			PlanificacionActividad a);

	public abstract List<MaterialActividadPlanificada> listarPorPrestarMantenimiento(
			PlanificacionActividad a);

	public abstract List<MaterialActividadPlanificada> listarMateriales(
			PlanificacionActividad planActividad);

}
