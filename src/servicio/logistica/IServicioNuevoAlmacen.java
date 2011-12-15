package servicio.logistica;

import java.util.List;

import modelo.Almacen;



public interface IServicioNuevoAlmacen {
	
	public abstract void eliminar(Almacen a);
	
	public abstract void agregar(Almacen a);
		
	public abstract void actualizar(Almacen a);
	
	public List<Almacen> listarAlmacen();
	
	public String generarCodigo();
}
