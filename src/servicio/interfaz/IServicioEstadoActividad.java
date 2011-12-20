package servicio.interfaz;

import modelo.EstadoActividad;

public interface IServicioEstadoActividad {
	
	public abstract void eliminar(EstadoActividad ea);
	
	public abstract void agregar(EstadoActividad ea);
		
	public abstract void actualizar(EstadoActividad ea);

}
