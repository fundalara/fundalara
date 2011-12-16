package servicio.competencia;

import java.util.List;

import modelo.Material;

public interface IServicioControlInventario {

   public abstract void eliminar(Material ci);
	
   public abstract void agregar(Material ci);
		
   public abstract void actualizar(Material ci);
   
   public abstract List<Material> listarActivos();
   
   public abstract  List<Material> listar();
}
