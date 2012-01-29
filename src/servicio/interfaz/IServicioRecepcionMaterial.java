package servicio.interfaz;

import java.util.List;

import modelo.RecepcionMaterial;

public interface IServicioRecepcionMaterial {
	
	public abstract void eliminar(RecepcionMaterial rm);
	
	public abstract void agregar(RecepcionMaterial rm);
		
	public abstract void actualizar(RecepcionMaterial rm);
	
	public String generarCodigo();
	
	public List<RecepcionMaterial> listarMateriales();

}
