package servicio.interfaz;

import java.util.List;

import modelo.Juego;
import modelo.PersonalForaneo;
import modelo.PersonalForaneoJuego;

public interface IServicioPersonalForaneoJuego {
	
	public abstract void eliminar(PersonalForaneoJuego p);

	public abstract void agregar(PersonalForaneoJuego p);
	
	public abstract  List<PersonalForaneoJuego> listar ();
	
	public abstract List<PersonalForaneoJuego> listarActivos();
	
	public abstract List<PersonalForaneoJuego> listarUmpireJuego (Juego j);
	

}
