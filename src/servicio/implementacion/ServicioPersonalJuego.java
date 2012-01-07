package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonalJuego;

import modelo.PersonalJuego;
import servicio.interfaz.IServicioPersonalJuego;

public class ServicioPersonalJuego implements IServicioPersonalJuego {
	
	DaoPersonalJuego daoPersonalJuego;

	public DaoPersonalJuego getDaoPersonalJuego() {
		return daoPersonalJuego;
	}

	public void setDaoPersonalJuego(DaoPersonalJuego daoPersonalJuego) {
		this.daoPersonalJuego = daoPersonalJuego;
	}

	@Override
	public void eliminar(PersonalJuego p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(PersonalJuego p) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PersonalJuego> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonalJuego> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
