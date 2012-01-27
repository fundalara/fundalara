package servicio.interfaz;

import modelo.PersonalActividadPlanificada;

public interface IServicioPersonalActividadPlanificada {
	
	public abstract void eliminar(PersonalActividadPlanificada pap);
	
	public abstract void agregar(PersonalActividadPlanificada pap);
		
	public abstract void actualizar(PersonalActividadPlanificada pap);

}
