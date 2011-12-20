package servicio.interfaz;

import java.util.List;
import modelo.PlanRotacion;

public interface IServicioPlanRotacion {
	public abstract void guardar(PlanRotacion pr);
	
	public abstract void actualizar(PlanRotacion pr);
	
	public abstract void eliminar(PlanRotacion pr);
	
	public abstract List<PlanRotacion> listar();
}
