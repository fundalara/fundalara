package servicio.interfaz;

import java.util.List;

import modelo.PersonaNatural;

public interface IServicoPersonaNatural {

	public abstract void eliminar(PersonaNatural c);
	
	public abstract void agregar(PersonaNatural c);
		
	public abstract void actualizar(PersonaNatural c);	
	
	public abstract List listar();

}
