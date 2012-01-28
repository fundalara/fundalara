package servicio.interfaz;

import java.util.List;

import modelo.Equipo;
import modelo.Horario;
import modelo.HorarioPlanTemporada;
import modelo.PlanTemporada;

public interface IServicioHorarioPlanTemporada {

	public abstract void guardar(HorarioPlanTemporada horarioPlanTemporada);
	
	public abstract void actualizar(HorarioPlanTemporada horarioPlanTemporada);
	
	public abstract void eliminar(HorarioPlanTemporada horarioPlanTemporada);
	
	public abstract HorarioPlanTemporada buscar(int codigo);
	
	public abstract HorarioPlanTemporada buscarHorarioPlanTemporadaEquipo(Horario horario, PlanTemporada planTemporada, Equipo equipo);
	
	public abstract List<HorarioPlanTemporada> listar();
	
	public abstract List<HorarioPlanTemporada> listarActivos();
	
	public abstract List<HorarioPlanTemporada> listarPorPlanTemporada(PlanTemporada planTemporada);
	
	public abstract List<HorarioPlanTemporada> listarPorPlanTemporadaHorario(PlanTemporada planTemporada, Horario horario);
	
}
