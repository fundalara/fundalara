package servicio.interfaz;

import java.util.List;

import modelo.PlanEntrenamiento;

public interface IServicioPlanEntrenamiento {
	
	public abstract void guardar(PlanEntrenamiento pe);
	
	public abstract void actualizar(PlanEntrenamiento pe);
	
	public abstract void eliminar(PlanEntrenamiento pe);
	
	public abstract List<PlanEntrenamiento> listar(); 


}
