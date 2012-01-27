package servicio.interfaz;

import java.util.List;

import modelo.PersonaJuridica;
import modelo.Personal;

public interface IServicioPersonal {
	
	public abstract void eliminar(Personal c);
	
	public abstract void agregar(Personal c);
		
	public abstract void actualizar(Personal  c);	
	
	public abstract  List<Personal> listar ();
	
	public abstract List<Personal> listarActivos();
	
	public abstract Personal buscarPorCodigo (Personal d);

}
