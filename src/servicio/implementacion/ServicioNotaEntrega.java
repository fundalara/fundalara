package servicio.implementacion;

import dao.general.DaoNotaEntrega;
import modelo.NotaEntrega;
import servicio.interfaz.IServicioNotaEntrega;

public class ServicioNotaEntrega implements IServicioNotaEntrega {

	DaoNotaEntrega daoNotaEntrega;
	
	@Override
	public void eliminar(NotaEntrega ne) {
		// TODO Auto-generated method stub
		daoNotaEntrega.eliminar(ne);
	}

	@Override
	public void agregar(NotaEntrega ne) {
		// TODO Auto-generated method stub
		daoNotaEntrega.guardar(ne);
	}

	@Override
	public void actualizar(NotaEntrega ne) {
		// TODO Auto-generated method stub
		daoNotaEntrega.actualizar(ne);
	}

	public DaoNotaEntrega getDaoNotaEntrega() {
		return daoNotaEntrega;
	}

	public void setDaoNotaEntrega(DaoNotaEntrega daoNotaEntrega) {
		this.daoNotaEntrega = daoNotaEntrega;
	}

}
