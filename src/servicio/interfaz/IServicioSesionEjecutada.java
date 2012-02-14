package servicio.interfaz;

import java.util.Date;
import java.util.List;

import modelo.Equipo;
import modelo.Sesion;
import modelo.SesionEjecutada;

public interface IServicioSesionEjecutada {

	public abstract void guardar(SesionEjecutada se);
	
	public abstract void actualizar(SesionEjecutada se);
	
	public abstract void eliminar(SesionEjecutada se);
	
	public abstract List<SesionEjecutada> listar(); 
	
	public abstract SesionEjecutada buscarPorFechaHoraEquipo(Equipo equipo,Date fecha,Date horaFin,Date horaInicio); 
	
	public abstract SesionEjecutada buscarSesionFecha(Sesion sesion, Date fecha);
}
