package servicio.interfaz;

import java.util.Date;
import java.util.List;

import modelo.Actividad;
import modelo.PlanificacionActividad;
import modelo.TareaActividad;

public interface IServicioActividad {

	public abstract void eliminar(Actividad a);

	public abstract void agregar(Actividad a);

	public abstract void actualizar(Actividad a);

	public abstract Actividad buscarActividad(PlanificacionActividad a);

	public Actividad Buscar(PlanificacionActividad a, Class<Actividad> class1);

	public abstract List<Actividad> listarActivos();

	public abstract List<TareaActividad> listar(Actividad actividad);

	public abstract List<TareaActividad> listar();

	public abstract List<Actividad> listarMantenimientos(Date fechaInicio, Date fechaFin);

	public abstract List<Actividad> listarComplementarias(Date fechaInicio, Date fechaFin);

	public abstract List<Actividad> listarComplementarias();

}
