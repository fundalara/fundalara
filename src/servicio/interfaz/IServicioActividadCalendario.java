package servicio.interfaz;

import java.util.Date;

import modelo.ActividadCalendario;
import modelo.Sesion;

public interface IServicioActividadCalendario {
	
	public abstract void eliminar(ActividadCalendario ac);
	
	public abstract void agregar(ActividadCalendario ac);
		
	public abstract void actualizar(ActividadCalendario ac);
	
	public abstract ActividadCalendario buscarSesionFecha(Date fecha, Sesion sesion);


}
