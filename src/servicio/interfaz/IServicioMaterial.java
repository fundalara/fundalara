package servicio.interfaz;

import java.util.List;

import modelo.Material;

public interface IServicioMaterial {

	public abstract void eliminar(Material m);
	
	public abstract void agregar(Material m);
		
	public abstract void actualizar(Material m);
	
	public List<Material> listarMateriales();
	
//	public Material buscarPorCodigo(int cod);
	
//	public String generarCodigo();
	
}
