package servicio.interfaz;

import java.util.List;

import modelo.Actividad;
import modelo.ComisionActividad;
import modelo.ComisionActividadPlanificada;

public interface IServicioComisionActividad {

public abstract void eliminar(ComisionActividad a);
	
	public abstract void agregar(ComisionActividad a);
		
	public abstract void actualizar(ComisionActividad a);

	public abstract List<ComisionActividad> listar();

	public abstract List<ComisionActividad> listar(Actividad actividad);
	
}

