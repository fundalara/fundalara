package servicio.interfaz;

import java.util.List;

import modelo.DatoBasico;
import modelo.LapsoDeportivo;;

public interface IServicioLapsoDeportivo {

	public abstract void guardar(LapsoDeportivo ae);
	
	public abstract void actualizar(LapsoDeportivo ae);
	
	public abstract void eliminar(LapsoDeportivo ae);
	
	public abstract List<LapsoDeportivo> listar(); 
	
	public abstract List<LapsoDeportivo> listarActivos();
	
	public abstract LapsoDeportivo buscarDosCampos(DatoBasico d); 
	
	public abstract LapsoDeportivo buscarporTipoLapso(DatoBasico db);
}
