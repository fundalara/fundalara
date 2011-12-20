package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioRoster;

import dao.general.DaoRoster;

import modelo.Roster;

public class ServicioRoster implements IServicioRoster {
	DaoRoster daoRoster;
	
	public DaoRoster getDaoRoster() {
		return daoRoster;
	}

	public void setDaoRoster(DaoRoster daoRoster) {
		this.daoRoster = daoRoster;
	}

	@Override
	public void eliminar(Roster c) {
		daoRoster.eliminar(c);

	}

	@Override
	public void agregar(Roster c) {
		daoRoster.guardar(c);

	}

	@Override
	public void actualizar(Roster c) {
		daoRoster.actualizar(c);

	}

	@Override
	public List<Roster> listar() {
		return daoRoster.listar( Roster.class);
	}

}
