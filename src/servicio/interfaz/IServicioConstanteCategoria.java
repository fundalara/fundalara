package servicio.interfaz;

import java.util.List;
import modelo.ConstanteCategoria;



public interface IServicioConstanteCategoria {
	
	public abstract void eliminar(ConstanteCategoria c);

	public abstract void agregar(List<ConstanteCategoria> l);

	public abstract  List<ConstanteCategoria> listar ();
	
	//public abstract List<ConstanteCategoria> listarActivos();

}
