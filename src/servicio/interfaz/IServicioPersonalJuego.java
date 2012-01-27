package servicio.interfaz;

import java.util.List;
import modelo.PersonalJuego;

public interface IServicioPersonalJuego {
	
	public abstract void eliminar(PersonalJuego p);

	public abstract void agregar(PersonalJuego p);
	
	public abstract  List<PersonalJuego> listar ();
	
	public abstract List<PersonalJuego> listarActivos();

}
