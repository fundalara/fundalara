package servicio.interfaz;

import modelo.TareaActividadPlanificada;

public interface IServicioTareaActividadPlanificada {
	
	public abstract void eliminar(TareaActividadPlanificada tap);
	
	public abstract void agregar(TareaActividadPlanificada tap);
		
	public abstract void actualizar(TareaActividadPlanificada tap);

}
