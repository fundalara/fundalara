package servicio.implementacion;

import dao.general.DaoPeriodicidad;
import modelo.Periodicidad;
import servicio.interfaz.IServicioPeriodicidad;

public class ServicioPeriodicidad implements IServicioPeriodicidad {

	DaoPeriodicidad daoPeriodicidad;
	
	@Override
	public void eliminar(Periodicidad p) {
		// TODO Auto-generated method stub
		daoPeriodicidad.eliminar(p);
	}

	@Override
	public void agregar(Periodicidad p) {
		// TODO Auto-generated method stub
		daoPeriodicidad.guardar(p);
	}

	@Override
	public void actualizar(Periodicidad p) {
		// TODO Auto-generated method stub
		daoPeriodicidad.actualizar(p);
	}

	public DaoPeriodicidad getDaoPeriodicidad() {
		return daoPeriodicidad;
	}

	public void setDaoPeriodicidad(DaoPeriodicidad daoPeriodicidad) {
		this.daoPeriodicidad = daoPeriodicidad;
	}

}
