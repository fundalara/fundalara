package servicio.interfaz;

import java.util.List;

import modelo.Persona;
import modelo.PersonalActividad;

public interface IServicioPersonalActividad {
	
	public abstract void eliminar(PersonalActividad pa);
	
	public abstract void agregar(PersonalActividad pa);
		
	public abstract void actualizar(PersonalActividad pa);

	public abstract List<PersonalActividad> listar();
	
	public abstract PersonalActividad Buscar(Persona persona);

}
