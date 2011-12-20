package servicio.implementacion;

import dao.general.DaoTipoDato;
import servicio.interfaz.IServicioTipoDato;

public class ServicoTipoDato implements IServicioTipoDato {
    
	
	DaoTipoDato daoTipoDato;
	
	public DaoTipoDato getDaoTipoDato() {
		return daoTipoDato;
	}

	public void setDaoTipoDato(DaoTipoDato daoTipoDato) {
		this.daoTipoDato = daoTipoDato;
	}

	@Override
	public void guardar(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(Object o) {
		// TODO Auto-generated method stub

	}

}
