package servicio.entrenamiento;

import java.util.List;
import modelo.TipoIndicador;

public interface IServicioTipoIndicador {
	public abstract void eliminar(TipoIndicador ti);
	
	public abstract void agregar(TipoIndicador ti);
		
	public abstract void actualizar(TipoIndicador ti);	
	
	public abstract List<TipoIndicador> listar();

}
