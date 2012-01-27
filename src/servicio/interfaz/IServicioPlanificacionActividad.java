package servicio.interfaz;

import modelo.PlanificacionActividad;

public interface IServicioPlanificacionActividad {
	
	public abstract void eliminar(PlanificacionActividad pa);
	
	public abstract void agregar(PlanificacionActividad pa);
		
	public abstract void actualizar(PlanificacionActividad pa);

}
