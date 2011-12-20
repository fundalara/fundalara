package servicio.interfaz;

import java.util.List;
import modelo.LineUp;

public interface IServicioLineUp {
	
	public abstract void eliminar(LineUp l);

	public abstract void agregar(LineUp l);
	
	public abstract  List<LineUp> listar ();
	
	public abstract List<LineUp> listarActivos();

}
