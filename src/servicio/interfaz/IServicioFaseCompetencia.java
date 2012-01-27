package servicio.interfaz;

import java.util.List;

import modelo.Competencia;
import modelo.FaseCompetencia;
import modelo.Juego;

public interface IServicioFaseCompetencia {
    
	public abstract void eliminar(FaseCompetencia fc);
	
	public abstract void agregar(List<FaseCompetencia> lista,int comp);
	
	public abstract  List<FaseCompetencia> listar ();
	
	public abstract List<FaseCompetencia> listarActivos();
	
	public abstract  List<FaseCompetencia> buscarPorCodigo (FaseCompetencia fc);
	
	public abstract FaseCompetencia EquiposRegistrados(Competencia competencia);
	
	
}
