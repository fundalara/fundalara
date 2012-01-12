package servicio.interfaz;

import java.util.List;
import modelo.ConstanteCategoria;


public interface IServicioConstanteCategoria {
	public abstract void eliminar(ConstanteCategoria c);

	public abstract void agregar(ConstanteCategoria c);

	public abstract  List<ConstanteCategoria> listar ();
	
	public abstract List<ConstanteCategoria> listarActivos();

}
