package servicio.logistica;

import dao.general.GenericDAO;
import modelo.Comision;

public class ServicioComision implements IServicioComision {

	GenericDAO daoP;
	
	
	public GenericDAO getDaoP() {
		return daoP;
	}

	public void setDaoP(GenericDAO daoP) {
		this.daoP = daoP;
	}

	@Override
	public void eliminar(Comision m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(Comision m) {
		// TODO Auto-generated method stub
		daoP.guardar(m);

	}

	@Override
	public void actualizar(Comision m) {
		// TODO Auto-generated method stub

	}

	


}
