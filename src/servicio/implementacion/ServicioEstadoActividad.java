package servicio.implementacion;

import java.util.List;

import dao.general.DaoEstadoActividad;
import modelo.Actividad;
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

	@Override
	public EstadoActividad buscar(Actividad a) {
		return this.daoEstadoActividad.buscar(a);
	}

	@Override
	public List<EstadoActividad> listar() {
		return this.daoEstadoActividad.listar(EstadoActividad.class);
	}

}
