package servicio.interfaz;

import java.util.List;

import modelo.PlanTemporada;

public interface IServicioPlanTemporada {
	public abstract void guardar (PlanTemporada pt);
	
	public abstract void actualizar(PlanTemporada pt);
	
	public abstract void eliminar(PlanTemporada pt);
	
	public abstract List<PlanTemporada> listar(); 
}
