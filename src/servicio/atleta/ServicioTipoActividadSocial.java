package servicio.atleta;

import dao.general.GenericDAO;
import modelo.TipoActividadSocial;

public class ServicioTipoActividadSocial implements
		IServicioTipoActividadSocial {

	
	GenericDAO daoP;
	
	public GenericDAO getDaoP() {
		return daoP;
	}

	public void setDaoP(GenericDAO daoP) {
		this.daoP = daoP;
	}
	
	@Override
	public void eliminar(TipoActividadSocial c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(TipoActividadSocial c) {
		daoP.guardar(c);

	}

	@Override
	public void actualizar(TipoActividadSocial c) {
		// TODO Auto-generated method stub

	}

}
