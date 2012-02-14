package servicio.interfaz;

import java.util.List;

import modelo.PersonalSuplente;

public interface IServicioPersonalSuplente {
	public abstract void eliminar(PersonalSuplente ps);
	
	public abstract void agregar(PersonalSuplente ps);
		
	public abstract void actualizar(PersonalSuplente ps);
	
	public abstract List<PersonalSuplente> listar();
}
