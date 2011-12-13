package servicio.competencia;

import java.util.List;

import modelo.EstadoVenezuela;

public interface IServicioEstadoVenezuela {
	
	public abstract void eliminar(EstadoVenezuela e);
	
	public abstract void agregar(EstadoVenezuela e);
		
	public abstract void actualizar(EstadoVenezuela e);
	
	public abstract  EstadoVenezuela buscarPorCodigo (EstadoVenezuela e);
	
	public abstract  List<EstadoVenezuela> listar ();
	

}
