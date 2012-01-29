package servicio.interfaz;

import java.util.List;

import modelo.SolicitudMantenimiento;

public interface IServicioSolicitudMantenimiento {
	
	public abstract void eliminar(SolicitudMantenimiento sm);
	
	public abstract void agregar(SolicitudMantenimiento sm);
		
	public abstract void actualizar(SolicitudMantenimiento sm);
	
	public abstract List<SolicitudMantenimiento> listarActivos();
	
	public abstract List<SolicitudMantenimiento> listar();



}
