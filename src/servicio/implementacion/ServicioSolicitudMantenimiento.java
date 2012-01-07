package servicio.implementacion;

import dao.general.DaoSolicitudMantenimiento;
import modelo.SolicitudMantenimiento;
import servicio.interfaz.IServicioSolicitudMantenimiento;

public class ServicioSolicitudMantenimiento implements
		IServicioSolicitudMantenimiento {

	DaoSolicitudMantenimiento daoSolicitudMantenimiento;
	
	@Override
	public void eliminar(SolicitudMantenimiento sm) {
		// TODO Auto-generated method stub
		daoSolicitudMantenimiento.eliminar(sm);
	}

	@Override
	public void agregar(SolicitudMantenimiento sm) {
		// TODO Auto-generated method stub
		daoSolicitudMantenimiento.guardar(sm);
	}

	@Override
	public void actualizar(SolicitudMantenimiento sm) {
		// TODO Auto-generated method stub
		daoSolicitudMantenimiento.actualizar(sm);
	}

	public DaoSolicitudMantenimiento getDaoSolicitudMantenimiento() {
		return daoSolicitudMantenimiento;
	}

	public void setDaoSolicitudMantenimiento(
			DaoSolicitudMantenimiento daoSolicitudMantenimiento) {
		this.daoSolicitudMantenimiento = daoSolicitudMantenimiento;
	}

}
