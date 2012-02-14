package servicio.interfaz;

import java.util.List;

import modelo.Categoria;
import modelo.LapsoDeportivo;
import modelo.PlanTemporada;

public interface IServicioPlanTemporada {
	public abstract void guardar (PlanTemporada pt);
	
	public abstract void actualizar(PlanTemporada pt);
	
	public abstract void eliminar(PlanTemporada pt);
	
	public abstract List<PlanTemporada> listar(); 
	
	public abstract int generarCodigo();
	
	public abstract List<PlanTemporada> listarActivos();
	
	public abstract PlanTemporada buscarPorCategoriaLapDep(Categoria ct, LapsoDeportivo ld);
		
	public abstract List<PlanTemporada> buscarPorLapsoDeportivo(LapsoDeportivo ld);
}
