package servicio.implementacion;

import java.util.List;

import dao.general.DaoEstadio;

import modelo.Estadio;
import servicio.interfaz.IServicioEstadio;

public class ServicioEstadio implements IServicioEstadio {
	
	DaoEstadio daoEstadio;

	public DaoEstadio getDaoEstadio() {
		return daoEstadio;
	}

	public void setDaoEstadio(DaoEstadio daoEstadio) {
		this.daoEstadio = daoEstadio;
	}

	@Override
	public void eliminar(Estadio e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(Estadio e) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Estadio> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estadio> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
