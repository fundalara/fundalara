package servicio.interfaz;

import java.util.List;

import modelo.Actividad;
import modelo.EstadoActividad;

public interface IServicioEstadoActividad {
	
	public abstract void eliminar(EstadoActividad ea);
	
	public abstract void agregar(EstadoActividad ea);
		
	public abstract void actualizar(EstadoActividad ea);

	public abstract EstadoActividad buscar(Actividad a);

	public abstract List<EstadoActividad> listar();
	

}
