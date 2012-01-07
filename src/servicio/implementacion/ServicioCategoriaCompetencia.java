package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioCategoriaCompetencia;

import dao.general.DaoCategoriaCompetencia;

import modelo.Categoria;
import modelo.CategoriaCompetencia;
import modelo.Divisa;

public class ServicioCategoriaCompetencia implements
		IServicioCategoriaCompetencia {

	DaoCategoriaCompetencia daoCategoriaCompetencia;
	@Override
	public void eliminar(Categoria cc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(Categoria cc) {
		// TODO Auto-generated method stub

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
	public List<CategoriaCompetencia> listarCategoriaPorCompetencia(String codigo) {
		
		return daoCategoriaCompetencia.listarCategoriaPorCompetencia(CategoriaCompetencia.class,codigo);
	}

}
