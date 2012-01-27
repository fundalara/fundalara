package servicio.interfaz;

import java.util.List;

import modelo.ActividadEntrenamiento;
import modelo.Categoria;
import modelo.DatoBasico;

public interface IServicioActividadEntrenamiento {
	public abstract void eliminar(ActividadEntrenamiento a);
	
	public abstract void agregar(ActividadEntrenamiento a);
		
	public abstract void actualizar(ActividadEntrenamiento a);	
	
	public abstract List<ActividadEntrenamiento> listar();
	
<<<<<<< HEAD
	public ActividadEntrenamiento buscarClaveForegn(Categoria c, DatoBasico f,int idActividad);
=======
	public ActividadEntrenamiento buscarClaveForegn(Categoria c, DatoBasico f,Integer idActividad);
>>>>>>> c864edb8a790ceadbc0ca3d0a3dc0fc8d4a3ddce
	
	public List<ActividadEntrenamiento> buscarTodo(Categoria c, DatoBasico f);
	
	public List<ActividadEntrenamiento> buscarPorCategoria(Categoria c);
	
	public ActividadEntrenamiento buscarPorCodigo(Integer i);
}
