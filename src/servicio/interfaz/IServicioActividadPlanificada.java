package servicio.interfaz;

import java.util.List;


import modelo.ActividadPlanificada;

public interface IServicioActividadPlanificada {
	
	public abstract void guardar(ActividadPlanificada ap);
	
	public abstract void actualizar(ActividadPlanificada ap);
	
	public abstract void eliminar(ActividadPlanificada ap);
	
	public abstract List<ActividadPlanificada> listar(); 

}
