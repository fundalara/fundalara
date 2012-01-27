package servicio.implementacion;

import dao.general.DaoActividad;
import modelo.Actividad;
import servicio.interfaz.IServicioActividad;

public class ServicioActividad implements IServicioActividad {

	DaoActividad daoActividad;
	
	@Override
	public void eliminar(Actividad a) {
		// TODO Auto-generated method stub
		daoActividad.eliminar(a);
	}

	@Override
	public void agregar(Actividad a) {
		// TODO Auto-generated method stub
		daoActividad.guardar(a);
	}

	@Override
	public void actualizar(Actividad a) {
		// TODO Auto-generated method stub
		daoActividad.actualizar(a);
	}

	public DaoActividad getDaoActividad() {
		return daoActividad;
	}

	public void setDaoActividad(DaoActividad daoActividad) {
		this.daoActividad = daoActividad;
	}

}
