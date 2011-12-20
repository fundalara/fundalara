package servicio.interfaz;

import java.util.List;

import modelo.EscalaMedicion;

public interface IServicioEscalaMedicion {
	public abstract void eliminar(EscalaMedicion e);
	
	public abstract void agregar(EscalaMedicion e);
		
	public abstract void actualizar(EscalaMedicion e);	
	
	public abstract List<EscalaMedicion> listar();

	public abstract EscalaMedicion  buscar(String codigo);
}
