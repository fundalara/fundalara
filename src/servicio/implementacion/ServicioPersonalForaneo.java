package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonalForaneo;
import dao.general.DaoPersonalJuego;

import modelo.PersonalForaneo;
import servicio.interfaz.IServicioPersonalForaneo;

public class ServicioPersonalForaneo implements IServicioPersonalForaneo {
	
	DaoPersonalForaneo daoPersonalForaneo;

	public DaoPersonalForaneo getDaoPersonalForaneo() {
		return daoPersonalForaneo;
	}

	public void setDaoPersonalForaneo(DaoPersonalForaneo daoPersonalForaneo) {
		this.daoPersonalForaneo = daoPersonalForaneo;
	}

	@Override
	public void eliminar(PersonalForaneo p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(PersonalForaneo p) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PersonalForaneo> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonalForaneo> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
