package servicio.implementacion;

import java.util.List;

import dao.general.DaoSolicitudMantenimiento;
import modelo.SolicitudMantenimiento;
import servicio.interfaz.IServicioSolicitudMantenimiento;

public class ServicioSolicitudMantenimiento implements
		IServicioSolicitudMantenimiento {

	DaoSolicitudMantenimiento daoSolicitudMantenimiento = new DaoSolicitudMantenimiento();

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

	@Override
	public List<SolicitudMantenimiento> listarActivos() {
		System.out.println(daoSolicitudMantenimiento.listarActivos(
				SolicitudMantenimiento.class).size());
		return daoSolicitudMantenimiento
				.listarActivos(SolicitudMantenimiento.class);
	}

	@Override
	public List<SolicitudMantenimiento> listar() {
		// TODO Auto-generated method stub
		return daoSolicitudMantenimiento.listar(SolicitudMantenimiento.class);
	}

}
