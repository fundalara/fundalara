package servicio.implementacion;

import dao.general.DaoEstadoActividad;
import modelo.EstadoActividad;
import servicio.interfaz.IServicioEstadoActividad;

public class ServicioEstadoActividad implements IServicioEstadoActividad {

	DaoEstadoActividad daoEstadoActividad;
	
	@Override
	public void eliminar(EstadoActividad ea) {
		// TODO Auto-generated method stub
		daoEstadoActividad.eliminar(ea);
	}

	@Override
	public void agregar(EstadoActividad ea) {
		// TODO Auto-generated method stub
		daoEstadoActividad.guardar(ea);
	}

	@Override
	public void actualizar(EstadoActividad ea) {
		// TODO Auto-generated method stub
		daoEstadoActividad.actualizar(ea);
	}

	public DaoEstadoActividad getDaoEstadoActividad() {
		return daoEstadoActividad;
	}

	public void setDaoEstadoActividad(DaoEstadoActividad daoEstadoActividad) {
		this.daoEstadoActividad = daoEstadoActividad;
	}

}
