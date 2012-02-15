package servicio.interfaz;

import java.util.List;

import modelo.Rol;

public interface IServicioRol {
	
	public abstract void guardar(Rol u);

	public abstract void actualizar(Rol u);

	public abstract void eliminar(Rol u);

	public abstract List<Rol> listar();
	
	public abstract List<Rol> listarActivos();
	 
}
