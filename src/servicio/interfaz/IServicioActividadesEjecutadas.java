package servicio.interfaz;

import java.util.List;
import modelo.ActividadesEjecutadas;

public interface IServicioActividadesEjecutadas {

	public abstract void guardar(ActividadesEjecutadas ae);
	
	public abstract void actualizar(ActividadesEjecutadas ae);
	
	public abstract void eliminar(ActividadesEjecutadas ae);
	
	public abstract List<ActividadesEjecutadas> listar(); 
}
