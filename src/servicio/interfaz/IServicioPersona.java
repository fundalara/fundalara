package servicio.interfaz;

import java.util.List;

import modelo.Nomina;
import modelo.Persona;

public interface IServicioPersona {
	
	public abstract void eliminar(Persona c);
	
	public abstract void agregar(Persona c);
		
	public abstract void actualizar(Persona c);	
	
	public abstract  List<Persona> listar ();
	
	public abstract List<Persona> listarActivos();
	
	public abstract Persona buscarPorCodigo (Persona d);

	public abstract List<Persona> listarPorTipos(String tipoPersona);

	public abstract Persona buscarPorCodigo(String Cedula);
	
	public abstract List<Persona> listarTrabajadores();

}
