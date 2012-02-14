package servicio.interfaz;

import java.util.List;
import modelo.ActividadEjecutada;

public interface IServicioActividadesEjecutadas {

	public abstract void guardar(ActividadEjecutada ae);
	
	public abstract void actualizar(ActividadEjecutada ae);
	
	public abstract void eliminar(ActividadEjecutada ae);
	
	public abstract List<ActividadEjecutada> listar(); 
}
