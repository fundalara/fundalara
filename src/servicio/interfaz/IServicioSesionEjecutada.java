package servicio.interfaz;

import java.util.List;

import modelo.SesionEjecutada;

public interface IServicioSesionEjecutada {

	public abstract void guardar(SesionEjecutada se);
	
	public abstract void actualizar(SesionEjecutada se);
	
	public abstract void eliminar(SesionEjecutada se);
	
	public abstract List<SesionEjecutada> listar(); 
}
