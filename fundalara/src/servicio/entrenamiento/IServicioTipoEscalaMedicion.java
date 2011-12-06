package servicio.entrenamiento;

import java.util.List;
import modelo.TipoEscalaMedicion;

public interface IServicioTipoEscalaMedicion {

	public abstract void eliminar(TipoEscalaMedicion tem);
	
	public abstract void agregar(TipoEscalaMedicion tem);
		
	public abstract void actualizar(TipoEscalaMedicion tem);	
	
	public abstract List<TipoEscalaMedicion> listar();
}
