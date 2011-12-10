package servicio.entrenamiento;

import java.util.List;
import modelo.IndicadorActividadEscala;

public interface IServicioIndicadorActividadEscala {
	public abstract void eliminar(IndicadorActividadEscala iae);
	
	public abstract void agregar(IndicadorActividadEscala iae);
		
	public abstract void actualizar(IndicadorActividadEscala iae);	
	
	public abstract List<IndicadorActividadEscala> listar();


}
