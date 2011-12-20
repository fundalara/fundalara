package servicio.interfaz;

import modelo.Actividad;

public interface IServicioActividad {
	
	public abstract void eliminar(Actividad a);
	
	public abstract void agregar(Actividad a);
		
	public abstract void actualizar(Actividad a);

}
