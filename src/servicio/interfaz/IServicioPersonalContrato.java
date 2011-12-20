package servicio.interfaz;

import java.util.List;

import modelo.PersonalContrato ;

public interface IServicioPersonalContrato {
	
	public abstract void eliminar(PersonalContrato  c);
	
	public abstract void agregar(PersonalContrato   c);
		
	public abstract void actualizar(PersonalContrato   c);	
	
	public abstract List listar();

}
