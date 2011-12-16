package servicio.competencia;

import java.util.List;

import modelo.FaseCompetencia;

public interface IServicioFaseCompetencia {
    
	public abstract void eliminar(FaseCompetencia fc);
	
	public abstract void agregar(FaseCompetencia fc);
		
	public abstract void actualizar(FaseCompetencia fc);
	
	public abstract  List<FaseCompetencia> buscarPorCodigo (FaseCompetencia fc);
}
