package servicio.interfaz;

import java.util.Date;
import java.util.List;

import modelo.Categoria;
import modelo.CategoriaCompetencia;


public interface IServicioCategoriaCompetencia {

	public abstract void eliminar(Categoria cc);

	public abstract void agregar(Categoria cc);

	//public abstract void actualizar(Categoria cc);

	//public abstract Categoria buscarPorCodigo(Categoria cc);

	public abstract List<Categoria> listar();

	public abstract List<Categoria> listarActivos();
	
	public abstract List <CategoriaCompetencia> listarCategoriaPorCompetencia(int codigo);
	
	public abstract int getDuraccionCategoria (Categoria cat);
	
	public abstract Date getDuraccionCategoriaHora(Categoria cat);
}
