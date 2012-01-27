package servicio.interfaz;

import modelo.Periodicidad;

public interface IServicioPeriodicidad {
	
	public abstract void eliminar(Periodicidad p);
	
	public abstract void agregar(Periodicidad p);
		
	public abstract void actualizar(Periodicidad p);

}
