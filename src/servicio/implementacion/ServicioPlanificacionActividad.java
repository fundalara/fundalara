package servicio.implementacion;

import dao.general.DaoPlanificacionActividad;
import modelo.PlanificacionActividad;
import servicio.interfaz.IServicioPlanificacionActividad;

public class ServicioPlanificacionActividad implements
		IServicioPlanificacionActividad {

	DaoPlanificacionActividad daoPlanificacionActividad;
	
	@Override
	public void eliminar(PlanificacionActividad pa) {
		// TODO Auto-generated method stub
		daoPlanificacionActividad.eliminar(pa);
	}

	@Override
	public void agregar(PlanificacionActividad pa) {
		// TODO Auto-generated method stub
		daoPlanificacionActividad.guardar(pa);
	}

	@Override
	public void actualizar(PlanificacionActividad pa) {
		// TODO Auto-generated method stub
		daoPlanificacionActividad.actualizar(pa);
	}

	public DaoPlanificacionActividad getDaoPlanificacionActividad() {
		return daoPlanificacionActividad;
	}

	public void setDaoPlanificacionActividad(
			DaoPlanificacionActividad daoPlanificacionActividad) {
		this.daoPlanificacionActividad = daoPlanificacionActividad;
	}

}
