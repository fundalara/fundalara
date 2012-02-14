package servicio.interfaz;

import java.util.List;

import modelo.ActividadEntrenamiento;
import modelo.IndicadorActividadEscala;

public interface IServicioIndicadorActividadEscala {

    public abstract void guardar(IndicadorActividadEscala iae);
	
    public abstract void actualizar(IndicadorActividadEscala iae);
	
	public abstract void eliminar(IndicadorActividadEscala iae);
	
	public abstract List<IndicadorActividadEscala> listar();
	
	public abstract List<IndicadorActividadEscala> buscarporObjeto(Integer iae,String campo);
	
	public abstract IndicadorActividadEscala buscarporCodigo (Integer codigo);
	
	public abstract List<IndicadorActividadEscala> buscarporActividad(ActividadEntrenamiento ae);
}
