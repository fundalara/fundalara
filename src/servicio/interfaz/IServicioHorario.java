package servicio.interfaz;

import java.util.Date;
import java.util.List;
import modelo.Horario;
import modelo.PlanTemporada;

public interface IServicioHorario {
	public abstract void guardar(Horario h);
	
	public abstract void actualizar(Horario h);
	
	public abstract void eliminar(Horario h);
	
	public abstract List<Horario> listar();
	
	public abstract List<Horario> listarPorPlanTemporada(PlanTemporada pt);
	
	public abstract Boolean compararRangoHoras(PlanTemporada pt, Date hIni, Date hFin);
}
