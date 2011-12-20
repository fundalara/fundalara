package servicio.interfaz;

import java.util.List;
import modelo.ActividadEntrenamiento;

public interface IServicioActividadEntrenamiento {

	public abstract void guardar(ActividadEntrenamiento ae);
	
	public abstract void actualizar(ActividadEntrenamiento ae);
	
	public abstract void eliminar(ActividadEntrenamiento ae);
	
	public abstract List<ActividadEntrenamiento> listar(); 
}
