package servicio.interfaz;

import modelo.PersonalActividad;

public interface IServicioPersonalActividad {
	
	public abstract void eliminar(PersonalActividad pa);
	
	public abstract void agregar(PersonalActividad pa);
		
	public abstract void actualizar(PersonalActividad pa);

}
