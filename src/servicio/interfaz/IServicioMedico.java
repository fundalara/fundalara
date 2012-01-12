package servicio.interfaz;

import java.util.List;
import modelo.Medico;

public interface IServicioMedico {
public abstract void eliminar(Medico c);
	
	public abstract void agregar(Medico c);
		
	public abstract void actualizar(Medico c);	
	
	public abstract Medico buscar(String id);
	
	/*public abstract Medico buscar (Medico c);*/
	
	public abstract List<Medico> listar();
}
