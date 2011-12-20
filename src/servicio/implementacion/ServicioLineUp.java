package servicio.implementacion;

import java.util.List;

import dao.general.DaoLineUp;

import modelo.LineUp;
import servicio.interfaz.IServicioLineUp;

public class ServicioLineUp implements IServicioLineUp {
	
	DaoLineUp daoLineUp;

	public DaoLineUp getDaoLineUp() {
		return daoLineUp;
	}

	public void setDaoLineUp(DaoLineUp daoLineUp) {
		this.daoLineUp = daoLineUp;
	}

	@Override
	public void eliminar(LineUp l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(LineUp l) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<LineUp> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LineUp> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
