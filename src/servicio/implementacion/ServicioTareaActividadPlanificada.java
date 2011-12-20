package servicio.implementacion;

import dao.general.DaoTareaActividadPlanificada;
import modelo.TareaActividadPlanificada;
import servicio.interfaz.IServicioTareaActividadPlanificada;

public class ServicioTareaActividadPlanificada implements
		IServicioTareaActividadPlanificada {

	DaoTareaActividadPlanificada daoTareaActividadPlanificada;
	
	@Override
	public void eliminar(TareaActividadPlanificada tap) {
		// TODO Auto-generated method stub
		daoTareaActividadPlanificada.eliminar(tap);
	}

	@Override
	public void agregar(TareaActividadPlanificada tap) {
		// TODO Auto-generated method stub
		daoTareaActividadPlanificada.guardar(tap);
	}

	@Override
	public void actualizar(TareaActividadPlanificada tap) {
		// TODO Auto-generated method stub
		daoTareaActividadPlanificada.actualizar(tap);
	}

	public DaoTareaActividadPlanificada getDaoTareaActividadPlanificada() {
		return daoTareaActividadPlanificada;
	}

	public void setDaoTareaActividadPlanificada(
			DaoTareaActividadPlanificada daoTareaActividadPlanificada) {
		this.daoTareaActividadPlanificada = daoTareaActividadPlanificada;
	}

}
