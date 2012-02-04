package servicio.interfaz;

import java.util.List;

import modelo.Actividad;
import modelo.MaterialActividad;

public interface IServicioMaterialActividad {
	
	public abstract void eliminar(MaterialActividad ma);
	
	public abstract void agregar(MaterialActividad ma);
		
	public abstract void actualizar(MaterialActividad ma);

	public abstract List<MaterialActividad> listar();

	public abstract List<MaterialActividad> listarPorDevolver(Actividad a);

	public abstract List<MaterialActividad> listarPorDevolverCompetencia(Actividad a);

	public abstract List<MaterialActividad> listarPorDevolverEvento(Actividad a);

	public abstract List<MaterialActividad> listarPorDevolverEntrenamiento(Actividad a);

	public abstract List<MaterialActividad> listarPorDevolverMantenimiento(Actividad a);

	public abstract List<MaterialActividad> ListarPorActividad(
			Actividad actividad);

}
