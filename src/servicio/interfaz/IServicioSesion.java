package servicio.interfaz;

import java.util.List;

import modelo.Equipo;
import modelo.PlanEntrenamiento;
import modelo.Sesion;

public interface IServicioSesion {
	public abstract void guardar(Sesion s);
	
	public abstract void actualizar(Sesion s);
	
	public abstract void eliminar(Sesion s);
	
	public abstract List<Sesion> listar();
	
	public abstract List<Sesion> buscarPorEquipo(Equipo e);
	
	public abstract Sesion buscarPorCodigo(Integer codigo);
	
	public abstract List<Sesion> buscarporPlanEntrenamiento(PlanEntrenamiento pe);
}
