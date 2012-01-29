package servicio.interfaz;

import java.util.List;

import modelo.Actividad;
import modelo.PlanificacionActividad;

public interface IServicioActividad {
	
	public abstract void eliminar(Actividad a);
	
	public abstract void agregar(Actividad a);
		
	public abstract void actualizar(Actividad a);

	public abstract Actividad buscarActividad(PlanificacionActividad a);

	public Actividad Buscar(PlanificacionActividad a, Class<Actividad> class1);
	
	public abstract List<Actividad> listarActivos();

}
