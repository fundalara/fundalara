package servicio.interfaz;
import java.util.List;

import modelo.Categoria;

public interface IServicioCategoria {

	public abstract void eliminar(Categoria c);
	
	public abstract void agregar(Categoria c);
		
	public abstract void actualizar(Categoria c);	
	
	public abstract List<Categoria> listar();
}
