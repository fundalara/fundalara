package servicio.interfaz;

import java.util.List;
import modelo.Medico;

public interface IServicioMedico {
	
	public abstract void eliminar(Medico c);
	
	public abstract void agregar(Medico c);
		
	public abstract void actualizar(Medico c);	
	
	public abstract Medico buscar(String id);
	
	public abstract List<Medico> listar();
	
	public abstract boolean buscarelimi(Medico medico);
	
}
