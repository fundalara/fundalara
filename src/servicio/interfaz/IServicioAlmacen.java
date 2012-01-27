package servicio.interfaz;

import java.util.List;

import modelo.Almacen;
import modelo.Instalacion;



public interface IServicioAlmacen {
	
	public abstract void eliminar(Almacen a);
	
	public abstract void agregar(Almacen a);
		
	public abstract void actualizar(Almacen a);
	
//	public List<Almacen> listarAlmacen();
//	
//	public String generarCodigo();
	
	public Instalacion buscarInstalacion(Almacen a);
	
	public List<Almacen> listarActivos();
}
