package servicio.interfaz;

import java.util.List;

import modelo.Competencia;
import modelo.EquipoFaseCompetencia;
import modelo.FaseCompetencia;
import modelo.Juego;

public interface IServicioFaseCompetencia {
    
	public abstract void eliminar(FaseCompetencia fc);
	
	public abstract void agregar(List<FaseCompetencia> lista,int comp);
	
	public abstract  List<FaseCompetencia> listar ();
	
	public abstract List<FaseCompetencia> listarActivos();
	
	public abstract  List<FaseCompetencia> buscarPorCodigo (FaseCompetencia fc);
	
	public abstract FaseCompetencia EquiposRegistrados(Competencia competencia);

	public abstract List<FaseCompetencia> listarFaseCompetencia(Competencia cp);

	public abstract List<FaseCompetencia> listarPorCompetencia(int codigo);

	public abstract void actualizar(List<FaseCompetencia> lista1, Competencia comp);
	
	public FaseCompetencia buscarFaseCompetencia(Competencia c, int numero);
	
	public abstract List<EquipoFaseCompetencia> listarPorCompetencia(Competencia comp);

	public abstract void actualizar1(FaseCompetencia faseCompetencia);
	
	
}
