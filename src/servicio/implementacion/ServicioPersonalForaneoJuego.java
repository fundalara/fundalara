package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonalForaneoJuego;

import modelo.PersonalForaneoJuego;
import servicio.interfaz.IServicioPersonalForaneoJuego;

public class ServicioPersonalForaneoJuego implements
		IServicioPersonalForaneoJuego {
	
	DaoPersonalForaneoJuego daoPersonalForaneoJuego;

	public DaoPersonalForaneoJuego getDaoPersonalForaneoJuego() {
		return daoPersonalForaneoJuego;
	}

	public void setDaoPersonalForaneoJuego(
			DaoPersonalForaneoJuego daoPersonalForaneoJuego) {
		this.daoPersonalForaneoJuego = daoPersonalForaneoJuego;
	}

	@Override
	public void eliminar(PersonalForaneoJuego p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(PersonalForaneoJuego p) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PersonalForaneoJuego> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonalForaneoJuego> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
