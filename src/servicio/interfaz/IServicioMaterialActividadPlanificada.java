package servicio.interfaz;

import modelo.MaterialActividadPlanificada;

public interface IServicioMaterialActividadPlanificada {
	
	public abstract void eliminar(MaterialActividadPlanificada map);
	
	public abstract void agregar(MaterialActividadPlanificada map);
		
	public abstract void actualizar(MaterialActividadPlanificada map);

}
