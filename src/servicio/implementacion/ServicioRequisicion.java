package servicio.implementacion;

import dao.general.DaoRequisicion;
import modelo.Requisicion;
import servicio.interfaz.IServicioRequisicion;

public class ServicioRequisicion implements IServicioRequisicion {

	DaoRequisicion daoRequisicion;
	
	@Override
	public void eliminar(Requisicion r) {
		// TODO Auto-generated method stub
		daoRequisicion.eliminar(r);
	}

	@Override
	public void agregar(Requisicion r) {
		// TODO Auto-generated method stub
		daoRequisicion.guardar(r);
	}

	@Override
	public void actualizar(Requisicion r) {
		// TODO Auto-generated method stub
		daoRequisicion.actualizar(r);
	}

	public DaoRequisicion getDaoRequisicion() {
		return daoRequisicion;
	}

	public void setDaoRequisicion(DaoRequisicion daoRequisicion) {
		this.daoRequisicion = daoRequisicion;
	}

}
