package servicio.interfaz;

import java.util.List;
import modelo.Sesion;

public interface IServicioSesion {
	public abstract void guardar(Sesion s);
	
	public abstract void actualizar(Sesion s);
	
	public abstract void eliminar(Sesion s);
	
	public abstract List<Sesion> listar();
}
