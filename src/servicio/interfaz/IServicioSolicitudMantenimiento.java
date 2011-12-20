package servicio.interfaz;

import modelo.SolicitudMantenimiento;

public interface IServicioSolicitudMantenimiento {
	
	public abstract void eliminar(SolicitudMantenimiento sm);
	
	public abstract void agregar(SolicitudMantenimiento sm);
		
	public abstract void actualizar(SolicitudMantenimiento sm);

}
