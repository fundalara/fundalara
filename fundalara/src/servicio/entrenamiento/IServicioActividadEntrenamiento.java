package servicio.entrenamiento;

import java.util.List;

import modelo.ActividadEntrenamiento;

public interface IServicioActividadEntrenamiento {
	public abstract void eliminar(ActividadEntrenamiento a);
	
	public abstract void agregar(ActividadEntrenamiento a);
		
	public abstract void actualizar(ActividadEntrenamiento a);	
	
	public abstract List<ActividadEntrenamiento> listar();


}
