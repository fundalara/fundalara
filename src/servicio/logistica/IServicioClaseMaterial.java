package servicio.logistica;

import java.util.List;

import modelo.ClaseMaterial;

public interface IServicioClaseMaterial {

	public abstract void eliminar(ClaseMaterial m);
	
	public abstract void agregar(ClaseMaterial m);
		
	public abstract void actualizar(ClaseMaterial m);	
	
	public abstract List<ClaseMaterial> listarClasesMateriales();
		
	public String generarCodigo();
}
