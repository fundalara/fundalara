package servicio.implementacion;

import dao.general.DaoPersonalActividadPlanificada;
import modelo.PersonalActividadPlanificada;
import servicio.interfaz.IServicioPersonalActividadPlanificada;

public class ServicioPersonalActividadPlanificada implements
		IServicioPersonalActividadPlanificada {

	DaoPersonalActividadPlanificada daoPersonalActividadPlanificada;
	
	@Override
	public void eliminar(PersonalActividadPlanificada pap) {
		// TODO Auto-generated method stub
		daoPersonalActividadPlanificada.eliminar(pap);
	}

	@Override
	public void agregar(PersonalActividadPlanificada pap) {
		// TODO Auto-generated method stub
		daoPersonalActividadPlanificada.guardar(pap);
	}

	@Override
	public void actualizar(PersonalActividadPlanificada pap) {
		// TODO Auto-generated method stub
		daoPersonalActividadPlanificada.actualizar(pap);
	}

	public DaoPersonalActividadPlanificada getDaoPersonalActividadPlanificada() {
		return daoPersonalActividadPlanificada;
	}

	public void setDaoPersonalActividadPlanificada(
			DaoPersonalActividadPlanificada daoPersonalActividadPlanificada) {
		this.daoPersonalActividadPlanificada = daoPersonalActividadPlanificada;
	}

}
