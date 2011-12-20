package servicio.interfaz;

import java.util.List;
import modelo.Estadio;

public interface IServicioEstadio {
	
	public abstract void eliminar(Estadio e);

	public abstract void agregar(Estadio e);
	
	public abstract  List<Estadio> listar ();
	
	public abstract List<Estadio> listarActivos();

}
