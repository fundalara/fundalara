package servicio.interfaz;

import java.util.List;
import modelo.Liga;


public interface IServicioLiga {
    
	public abstract void eliminar(Liga l);
	
	public abstract void agregar(Liga l);
		
	//public abstract void actualizar(Liga l);
	
	//public abstract  List<Liga> buscarPorCodigo (Liga l);
	
	public abstract  List<Liga> listar ();
	
	public abstract List<Liga> listarActivos();
}
