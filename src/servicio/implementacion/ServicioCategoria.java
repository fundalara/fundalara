package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioCategoria;

import dao.general.DaoCategoria;
import modelo.Categoria;


/**
 * Clase para brindar los servicios para manejar los datos relacionados con las categorias de los jugadores
 * @author Robert A
 * @author German L
 * @version 0.1 29/12/2011
 *
 */
public class ServicioCategoria implements IServicioCategoria {

	DaoCategoria daoCategoria;
	
	public DaoCategoria getDaoCategoria() {
		return daoCategoria;
	}

	public void setDaoCategoria(DaoCategoria daoCategoria) {
		this.daoCategoria = daoCategoria;
	}

	
	
	@Override
	public List<Categoria> listarActivos() {
		// TODO Auto-generated method stub
		return daoCategoria.listarActivos();
	}

	

	@Override
	public List<Categoria> buscarPorCategoria(Categoria c) {
		// TODO Auto-generated method stub
		return null;
	}
	
//
	
	
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
		return daoCategoria.listar(Categoria.class);
	}
	
	@Override
	public Categoria buscarPorEdad(int edad) {
		return daoCategoria.buscarPorEdad(edad);
	}
	
	@Override
	public List<Categoria> buscarCategorias(int edad){
		return daoCategoria.buscarCategorias(edad);
	}

	@Override
	public boolean buscarPorCodigo(Categoria categoria) {
		return daoCategoria.buscarPorCodigo(categoria);
	}
	
	
	public List<Categoria> buscarCategoriasPorEdad(int edad) {
		return daoCategoria.buscarCategoriasPorEdad(edad);
	}
	

	public List<Categoria> buscarCategoriasAscenso(int edad){
		return daoCategoria.buscarCategoriasAscenso(edad);
	}
	
	
}
