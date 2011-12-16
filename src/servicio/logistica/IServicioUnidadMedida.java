package servicio.logistica;

import java.util.List;

import modelo.UnidadMedida;


public interface IServicioUnidadMedida {

	public abstract void eliminar(UnidadMedida m);
	
	public abstract void agregar(UnidadMedida m);
		
	public abstract void actualizar(UnidadMedida m);	
	
	public abstract List<UnidadMedida> listarUnidadesMedida();
}
