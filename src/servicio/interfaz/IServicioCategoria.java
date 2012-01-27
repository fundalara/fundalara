package servicio.interfaz;

import java.util.List;

import modelo.Categoria;

/**
 * Interfaz  que define el contrato de los servicios  para el acceso y manejo de las categorias de los jugadores 
 * @author Robert A
 * @author German L
 * @version 0.1 30/12/2011
 *
 */
public interface IServicioCategoria {

	public abstract void eliminar(Categoria c);
	
	public abstract void agregar(Categoria c);
		
	public abstract void actualizar(Categoria c);
	
	public List<Categoria> listarActivos();
	
	public abstract List<Categoria> listar();
	
	public abstract Categoria buscarPorEdad(int edad);
	
	public abstract List<Categoria> buscarCategorias(int edad);
	
	public boolean buscarPorCodigo (Categoria categoria);

}
