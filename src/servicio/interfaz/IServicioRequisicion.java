package servicio.interfaz;

import modelo.Requisicion;

public interface IServicioRequisicion {
	
	public abstract void eliminar(Requisicion r);
	
	public abstract void agregar(Requisicion r);
		
	public abstract void actualizar(Requisicion r);

}
