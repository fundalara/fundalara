package servicio.implementacion;

import java.util.List;

import modelo.Actividad;
import modelo.DatoBasico;
import modelo.PlanificacionActividad;
import modelo.TareaActividadPlanificada;
import servicio.interfaz.IServicioTareaActividadPlanificada;
import dao.general.DaoTareaActividadPlanificada;

public class ServicioTareaActividadPlanificada implements IServicioTareaActividadPlanificada {

	DaoTareaActividadPlanificada daoTareaActividadPlanificada;

	@Override
	public void eliminar(TareaActividadPlanificada tap) {
		daoTareaActividadPlanificada.eliminar(tap);
	}

	@Override
	public void agregar(TareaActividadPlanificada tap) {
		daoTareaActividadPlanificada.guardar(tap);
	}

	@Override
	public void actualizar(TareaActividadPlanificada tap) {
		daoTareaActividadPlanificada.actualizar(tap);
	}

	public DaoTareaActividadPlanificada getDaoTareaActividadPlanificada() {
		return daoTareaActividadPlanificada;
	}

	public void setDaoTareaActividadPlanificada(DaoTareaActividadPlanificada daoTareaActividadPlanificada) {
		this.daoTareaActividadPlanificada = daoTareaActividadPlanificada;
	}

	@Override
	public List<TareaActividadPlanificada> listarTareas(PlanificacionActividad planActividad) {
		return daoTareaActividadPlanificada.listarTareas(planActividad);
	}

	@Override
	public List<TareaActividadPlanificada> listar() {
		return daoTareaActividadPlanificada.listar(TareaActividadPlanificada.class);
	}

	@Override
	public List<TareaActividadPlanificada> listarTareas(Actividad a) {
		return daoTareaActividadPlanificada.listarTareas(a);
	}

	public TareaActividadPlanificada buscarPorActividad(PlanificacionActividad planificacionActividad, DatoBasico tarea) {
		List<TareaActividadPlanificada> a = daoTareaActividadPlanificada.listar(TareaActividadPlanificada.class);
		TareaActividadPlanificada b = new TareaActividadPlanificada();
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).getPersonalActividadPlanificada() != null) {
				if (a.get(i).getDatoBasico().getCodigoDatoBasico() == tarea.getCodigoDatoBasico()
						&& a.get(i).getPlanificacionActividad().getCodigoPlanificacionActividad() == planificacionActividad
								.getCodigoPlanificacionActividad()) {
					b = a.get(i);
					break;
				}
			}

		}
		;

		return b;
	}

}
