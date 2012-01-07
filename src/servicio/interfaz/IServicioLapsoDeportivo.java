package servicio.interfaz;

import java.util.List;

import modelo.LapsoDeportivo;;

public interface IServicioLapsoDeportivo {

	public abstract void guardar(LapsoDeportivo ae);
	
	public abstract void actualizar(LapsoDeportivo ae);
	
	public abstract void eliminar(LapsoDeportivo ae);
	
	public abstract List<LapsoDeportivo> listar(); 
}
