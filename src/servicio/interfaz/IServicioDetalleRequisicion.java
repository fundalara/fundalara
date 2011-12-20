package servicio.interfaz;

import modelo.DetalleRequisicion;

public interface IServicioDetalleRequisicion {
	
	public abstract void eliminar(DetalleRequisicion dr);
	
	public abstract void agregar(DetalleRequisicion dr);
		
	public abstract void actualizar(DetalleRequisicion dr);

}
