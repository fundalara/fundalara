package servicio.interfaz;

import java.util.List;

import modelo.PersonalCargo;

public interface IServicioPersonalCargo {
	
	public abstract void eliminar(PersonalCargo c);
	
	public abstract void agregar(PersonalCargo c);
		
	public abstract void actualizar(PersonalCargo c);	
	
	public abstract List listar();


}
