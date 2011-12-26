package servicio.interfaz;

import java.util.List;

import modelo.Material;

public interface IServicioMaterial {

	public abstract void eliminar(Material m);
	
	public abstract void agregar(Material m);
		
	public abstract void actualizar(Material m);
	
	public abstract List<Material> listar();
	
	public abstract List<Material> listarActivos();
	
//	public Material buscarPorCodigo(int cod);
	
	public int generarCodigo();
	
}
