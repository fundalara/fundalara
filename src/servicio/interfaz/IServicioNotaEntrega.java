package servicio.interfaz;

import modelo.NotaEntrega;

public interface IServicioNotaEntrega {
	
	public abstract void eliminar(NotaEntrega ne);
	
	public abstract void agregar(NotaEntrega ne);
		
	public abstract void actualizar(NotaEntrega ne);

}
