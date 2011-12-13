package servicio.competencia;

import java.util.List;

import modelo.CategoriaCompetencia;

public interface IServicioCategoriaCompetencia {
	
	public abstract void eliminar(CategoriaCompetencia cc);
	
	public abstract void agregar(CategoriaCompetencia cc);
		
	public abstract void actualizar(CategoriaCompetencia cc);
	
	public abstract  List<CategoriaCompetencia> buscarPorCodigo (CategoriaCompetencia cc);

}
