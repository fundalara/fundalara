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
	
	public ActividadEntrenamiento buscarClaveForegn(Categoria c, DatoBasico f,Integer idActividad);
	
	public List<ActividadEntrenamiento> buscarTodo(Categoria c, DatoBasico f);
	
	public List<ActividadEntrenamiento> buscarPorCategoria(Categoria c);
	
	public ActividadEntrenamiento buscarPorCodigo(Integer i);
}
