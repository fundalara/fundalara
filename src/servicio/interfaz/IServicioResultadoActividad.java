package servicio.interfaz;

import modelo.ResultadoActividad;

public interface IServicioResultadoActividad {
	
	public abstract void eliminar(ResultadoActividad ra);
	
	public abstract void agregar(ResultadoActividad ra);
		
	public abstract void actualizar(ResultadoActividad ra);

}
