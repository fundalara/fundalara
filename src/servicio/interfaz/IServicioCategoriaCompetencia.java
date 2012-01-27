<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
package servicio.interfaz;

import java.util.Date;
import java.util.List;

import modelo.Categoria;
import modelo.CategoriaCompetencia;


public interface IServicioCategoriaCompetencia {

	public abstract void eliminar(Categoria cc);

	public abstract void agregar(List<CategoriaCompetencia> lista,int comp);

	//public abstract void actualizar(Categoria cc);

	//public abstract Categoria buscarPorCodigo(Categoria cc);

	public abstract List<Categoria> listar();

	public abstract List<Categoria> listarActivos();
	
	public abstract List <CategoriaCompetencia> listarCategoriaPorCompetencia(int codigo);
	
	public abstract int getDuraccionCategoria (Categoria cat);
	
	public abstract Date getDuraccionCategoriaHora(Categoria cat);
}
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
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
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
