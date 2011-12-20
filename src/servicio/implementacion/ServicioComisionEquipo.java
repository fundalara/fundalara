package servicio.implementacion;

import dao.general.DaoComisionEquipo;
import modelo.ComisionEquipo;
import servicio.interfaz.IServicioComisionEquipo;

public class ServicioComisionEquipo implements IServicioComisionEquipo {

	DaoComisionEquipo daoComisionEquipo;
	
	@Override
	public void eliminar(ComisionEquipo ce) {
		// TODO Auto-generated method stub
		daoComisionEquipo.eliminar(ce);
	}

	@Override
	public void agregar(ComisionEquipo ce) {
		// TODO Auto-generated method stub
		daoComisionEquipo.guardar(ce);
	}

	@Override
	public void actualizar(ComisionEquipo ce) {
		// TODO Auto-generated method stub
		daoComisionEquipo.actualizar(ce);
	}

	public DaoComisionEquipo getDaoComisionEquipo() {
		return daoComisionEquipo;
	}

	public void setDaoComisionEquipo(DaoComisionEquipo daoComisionEquipo) {
		this.daoComisionEquipo = daoComisionEquipo;
	}

}
