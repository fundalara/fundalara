package servicio.interfaz;

import java.util.List;

import modelo.Actividad;
import modelo.DatoBasico;
import modelo.PlanificacionActividad;
import modelo.TareaActividadPlanificada;

public interface IServicioTareaActividadPlanificada {

	public abstract void eliminar(TareaActividadPlanificada tap);

	public abstract void agregar(TareaActividadPlanificada tap);

	public abstract void actualizar(TareaActividadPlanificada tap);

	public abstract List<TareaActividadPlanificada> listarTareas(PlanificacionActividad planActividad);

	public abstract List<TareaActividadPlanificada> listar();

	public abstract List<TareaActividadPlanificada> listarTareas(Actividad a);

	public abstract TareaActividadPlanificada buscarPorActividad(PlanificacionActividad planificacionActividad, DatoBasico tarea);
}
