package servicio.interfaz;

import java.util.List;
import modelo.PersonalForaneo;

public interface IServicioPersonalForaneo {
	
	public abstract void eliminar(PersonalForaneo p);

	public abstract void agregar(PersonalForaneo p);
	
	public abstract  List<PersonalForaneo> listar ();
	
	public abstract List<PersonalForaneo> listarActivos();

}
