package servicio.interfaz;

import java.util.List;

import modelo.IndicadorActividadEscala;

public interface IServicioIndicadorActividadEscala {

    public abstract void guardar(IndicadorActividadEscala iae);
	
    public abstract void actualizar(IndicadorActividadEscala iae);
	
	public abstract void eliminar(IndicadorActividadEscala iae);
	
	public abstract List<IndicadorActividadEscala> listar();
}
