package servicio.implementacion;

import java.util.Date;
<<<<<<< HEAD
import java.util.Iterator;
=======
<<<<<<< HEAD
import java.util.Iterator;
=======
<<<<<<< HEAD
import java.util.Iterator;
=======
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
import java.util.List;

import servicio.interfaz.IServicioCategoriaCompetencia;

import dao.general.DaoCategoriaCompetencia;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.CategoriaCompetenciaId;
import modelo.Divisa;

public class ServicioCategoriaCompetencia implements
		IServicioCategoriaCompetencia {

	DaoCategoriaCompetencia daoCategoriaCompetencia;
	@Override
	public void eliminar(Categoria cc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(List<CategoriaCompetencia> lista,int comp) {
		// TODO Auto-generated method stub

		
//		int codigoCompetencia = daoCompetencia.listar(Competencia.class).size();
		
		for (Iterator i= lista.iterator(); i.hasNext();){
			CategoriaCompetencia id = (CategoriaCompetencia) i.next();
			
			id.getCompetencia().setCodigoCompetencia(comp);
			
			
			int codcateg = id.getCategoria().getCodigoCategoria();
			
			CategoriaCompetenciaId categID= new CategoriaCompetenciaId();
			categID.setCodigoCategoria(codcateg);
			categID.setCodigoCompetencia(comp);
			
			id.setId(categID);
			
			daoCategoriaCompetencia.guardar(id);		
	    }

//		daoCategoriaCompetencia.guardar(cc);		
				
	}


	/*@Override
	public void actualizar(Categoria cc) {
		// TODO Auto-generated method stub

	}*/

	/*@Override
	public Categoria buscarPorCodigo(Categoria cc) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<Categoria> listar() {
		return daoCategoriaCompetencia.listar(Categoria.class);
	}

	@Override
	public List<Categoria> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}
		

	public DaoCategoriaCompetencia getDaoCategoriaCompetencia() {
		return daoCategoriaCompetencia;
	}

	public void setDaoCategoriaCompetencia(
			DaoCategoriaCompetencia daoCategoriaCompetencia) {
		this.daoCategoriaCompetencia = daoCategoriaCompetencia;
	}

	@Override
	public List<CategoriaCompetencia> listarCategoriaPorCompetencia(int codigo) {
		
		return daoCategoriaCompetencia.listarCategoriaPorCompetencia(CategoriaCompetencia.class,codigo);
	}

	@Override
	public int getDuraccionCategoria(Categoria cat) {
		return daoCategoriaCompetencia.getDuraccionCategoria(cat);
		
	}

	@Override
	public Date getDuraccionCategoriaHora(Categoria cat) {
		return daoCategoriaCompetencia.getDuraccionCategoriaHoras(cat);
	}

}
