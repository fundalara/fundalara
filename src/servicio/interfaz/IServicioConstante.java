package servicio.interfaz;

import java.util.List;

import modelo.Constante;

public interface IServicioConstante {

	public abstract void eliminar(Constante c);

	public abstract void agregar(Constante c);
	
	public abstract  List<Constante> listar ();
	
	public abstract List<Constante> listarActivos();

}
