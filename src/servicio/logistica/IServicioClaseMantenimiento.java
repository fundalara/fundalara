package servicio.logistica;

import java.util.List;

import modelo.ClaseMantenimiento;

public interface IServicioClaseMantenimiento {
	
	public abstract void eliminar(ClaseMantenimiento c);
	
	public abstract void guardar(ClaseMantenimiento c);
	
	public abstract void actualizar(ClaseMantenimiento c);
	
	public abstract void buscar(ClaseMantenimiento c);
	
	public List<ClaseMantenimiento> listar();
	
	public String generarCodigo();
}
