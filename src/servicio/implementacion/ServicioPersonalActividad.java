package servicio.implementacion;

import dao.general.DaoPersonalActividad;
import modelo.PersonalActividad;
import servicio.interfaz.IServicioPersonalActividad;

public class ServicioPersonalActividad implements IServicioPersonalActividad {

	DaoPersonalActividad daoPersonalActividad;
	
	@Override
	public void eliminar(PersonalActividad pa) {
		// TODO Auto-generated method stub
		daoPersonalActividad.eliminar(pa);
	}

	@Override
	public void agregar(PersonalActividad pa) {
		// TODO Auto-generated method stub
		daoPersonalActividad.guardar(pa);
	}

	@Override
	public void actualizar(PersonalActividad pa) {
		// TODO Auto-generated method stub
		daoPersonalActividad.actualizar(pa);
	}

	public DaoPersonalActividad getDaoPersonalActividad() {
		return daoPersonalActividad;
	}

	public void setDaoPersonalActividad(DaoPersonalActividad daoPersonalActividad) {
		this.daoPersonalActividad = daoPersonalActividad;
	}

}
