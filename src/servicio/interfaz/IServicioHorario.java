package servicio.interfaz;

import java.util.List;
import modelo.Horario;

public interface IServicioHorario {
	public abstract void guardar(Horario h);
	
	public abstract void actualizar(Horario h);
	
	public abstract void eliminar(Horario h);
	
	public abstract List<Horario> listar(); 
}
