package servicio.interfaz;

import java.util.List;
import java.util.Set;

import modelo.Constante;
import modelo.ConstanteCategoria;

public interface IServicioConstante {

	public abstract void eliminar(Constante c);

	public abstract void agregar(Constante c);
	
	public abstract  List<Constante> listar ();
	
	public abstract List<Constante> listarActivos();
	
	public abstract List<Constante> listarConstantesActivos();

}
