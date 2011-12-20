package servicio.interfaz;

import java.util.List;

import modelo.Personal;

public interface IServicioPersonal {
	
	public abstract void eliminar(Personal c);
	
	public abstract void agregar(Personal c);
		
	public abstract void actualizar(Personal  c);	
	
	public abstract List listar();

}
