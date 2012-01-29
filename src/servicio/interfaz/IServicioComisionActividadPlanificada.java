package servicio.interfaz;

import java.util.List;

import modelo.ComisionActividadPlanificada;
import modelo.PlanificacionActividad;

public interface IServicioComisionActividadPlanificada {
	
	public abstract void eliminar(ComisionActividadPlanificada ce);
	
	public abstract void agregar(ComisionActividadPlanificada ce);
		
	public abstract void actualizar(ComisionActividadPlanificada ce);
	
	public abstract List<ComisionActividadPlanificada> listar();
	
	public abstract List<ComisionActividadPlanificada> listar(PlanificacionActividad a);

}
