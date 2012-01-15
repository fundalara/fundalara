package servicio.interfaz;

import modelo.RecepcionMaterial;

public interface IServicioRecepcionMaterial {
	
	public abstract void eliminar(RecepcionMaterial rm);
	
	public abstract void agregar(RecepcionMaterial rm);
		
	public abstract void actualizar(RecepcionMaterial rm);

}
