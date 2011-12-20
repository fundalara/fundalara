package servicio.interfaz;

import java.util.List;

import modelo.PersonaJuridica ;

public interface IServicioPersonaJuridica {

	public abstract void eliminar(PersonaJuridica c);
	
	public abstract void agregar(PersonaJuridica  c);
		
	public abstract void actualizar(PersonaJuridica  c);	
	
	public abstract List listar();

}
