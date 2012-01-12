package servicio.implementacion;

import java.util.List;

import dao.general.DaoConstanteCategoria;

import modelo.Constante;
import servicio.interfaz.IServicioConstante;

public class ServicioConstanteCategoria implements IServicioConstante {
	
	DaoConstanteCategoria daoConstanteCategoria;
	
	public DaoConstanteCategoria getDaoConstanteCategoria() {
		return daoConstanteCategoria;
	}

	public void setDaoConstanteCategoria(DaoConstanteCategoria daoConstanteCategoria) {
		this.daoConstanteCategoria = daoConstanteCategoria;
	}

	@Override
	public void eliminar(Constante c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(Constante c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Constante> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Constante> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
