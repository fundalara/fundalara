package servicio.interfaz;

import modelo.ActividadCalendario;

public interface IServicioActividadCalendario {
	
	public abstract void eliminar(ActividadCalendario ac);
	
	public abstract void agregar(ActividadCalendario ac);
		
	public abstract void actualizar(ActividadCalendario ac);

}
