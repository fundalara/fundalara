package servicio.interfaz;

import java.util.List;

import modelo.CategoriaLiga;


public interface IServicioCategoriaLiga {

	
   public abstract void eliminar(CategoriaLiga cl);
	
	public abstract void agregar(CategoriaLiga cl);
		
	public abstract  List<CategoriaLiga> listar ();
	
	public abstract List<CategoriaLiga> listarActivos();
}
