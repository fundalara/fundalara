package servicio.interfaz;

import java.util.List;

import modelo.Persona;
import modelo.PersonaNatural;

public interface IServicoPersonaNatural {

	public abstract void eliminar(PersonaNatural c);
	
	public abstract void agregar(PersonaNatural c);
		
	public abstract void actualizar(PersonaNatural c);	
	
	public abstract  List<PersonaNatural> listar ();
	
	public abstract List<PersonaNatural> listarActivos();
	
	public abstract PersonaNatural buscarPorCodigo (PersonaNatural d);


}
