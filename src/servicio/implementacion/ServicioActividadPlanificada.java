package servicio.implementacion;

import java.util.List;

import dao.general.DaoActividadEntrenamiento;
import dao.general.DaoActividadPlanificada;

import modelo.ActividadPlanificada;
import servicio.interfaz.IServicioActividadPlanificada;

public class ServicioActividadPlanificada implements
		IServicioActividadPlanificada {
	
	DaoActividadPlanificada daoActividadPlanificada;

	@Override
	public void guardar(ActividadPlanificada ap) {
		daoActividadPlanificada.guardar(ap);

	}

	@Override
	public void actualizar(ActividadPlanificada ap) {
		daoActividadPlanificada.actualizar(ap);

	}

	@Override
	public void eliminar(ActividadPlanificada ap) {
		daoActividadPlanificada.eliminar(ap);

	}

	@Override
	public List<ActividadPlanificada> listar() {	
		return daoActividadPlanificada.listar(ActividadPlanificada.class);
	}

	public DaoActividadPlanificada getDaoActividadPlanificada() {
		return daoActividadPlanificada;
	}

	public void setDaoActividadPlanificada(
			DaoActividadPlanificada daoActividadPlanificada) {
		this.daoActividadPlanificada = daoActividadPlanificada;
	}
	
}
