package servicio.implementacion;

import java.util.List;

import dao.general.DaoComisionActividadPlanificada;

import modelo.ComisionActividadPlanificada;
import modelo.PlanificacionActividad;
import servicio.interfaz.IServicioComisionActividadPlanificada;

public class ServicioComisionActividadPlanificada implements
		IServicioComisionActividadPlanificada {

	DaoComisionActividadPlanificada daoComisionActividadPlanificada;
	
	@Override
	public void eliminar(ComisionActividadPlanificada ce) {
		daoComisionActividadPlanificada.eliminar(ce);

	}

	@Override
	public void agregar(ComisionActividadPlanificada ce) {
		daoComisionActividadPlanificada.guardar(ce);

	}

	@Override
	public void actualizar(ComisionActividadPlanificada ce) {
       daoComisionActividadPlanificada.actualizar(ce); 
	}

	@Override
	public List<ComisionActividadPlanificada> listar(PlanificacionActividad  a) {
		return this.daoComisionActividadPlanificada.listarPorPlanificacion(a);
	}

	@Override
	public List<ComisionActividadPlanificada> listar() {
		
		return daoComisionActividadPlanificada.listar(ComisionActividadPlanificada.class);
	}

	public DaoComisionActividadPlanificada getDaoComisionActividadPlanificada() {
		return daoComisionActividadPlanificada;
	}

	public void setDaoComisionActividadPlanificada(
			DaoComisionActividadPlanificada daoComisionActividadPlanificada) {
		this.daoComisionActividadPlanificada = daoComisionActividadPlanificada;
	}

}
