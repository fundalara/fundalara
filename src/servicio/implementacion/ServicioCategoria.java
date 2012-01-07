package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioCategoria;

import dao.general.DaoCategoria;
import modelo.Categoria;

public class ServicioCategoria implements IServicioCategoria {

	DaoCategoria daoCategoria;
	
	public DaoCategoria getDaoCategoria() {
		return daoCategoria;
	}

	public void setDaoCategoria(DaoCategoria daoCategoria) {
		this.daoCategoria = daoCategoria;
	}

	@Override
	public void eliminar(Categoria c) {
		daoCategoria.eliminar(c);

	}

	@Override
	public void agregar(Categoria c) {
		daoCategoria.guardar(c);

	}

	@Override
	public void actualizar(Categoria c) {
		daoCategoria.actualizar(c);

	}

	@Override
	public List<Categoria> listar() {
		return daoCategoria.listar( Categoria.class);
	}

}
