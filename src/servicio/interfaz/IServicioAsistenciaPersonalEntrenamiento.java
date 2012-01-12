package servicio.interfaz;

import java.util.List;

import modelo.AsistenciaPersonalEntrenamiento;

public interface IServicioAsistenciaPersonalEntrenamiento {
	
	public abstract void guardar(AsistenciaPersonalEntrenamiento ape);
	
	public abstract void actualizar(AsistenciaPersonalEntrenamiento ape);
	
	public abstract void eliminar(AsistenciaPersonalEntrenamiento ape);
	
	public abstract List<AsistenciaPersonalEntrenamiento> listar(); 
}
