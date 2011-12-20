package servicio.interfaz;

import modelo.ComisionEquipo;

public interface IServicioComisionEquipo {
	
	public abstract void eliminar(ComisionEquipo ce);
	
	public abstract void agregar(ComisionEquipo ce);
		
	public abstract void actualizar(ComisionEquipo ce);

}
