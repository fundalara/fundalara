package servicio.implementacion;

import java.util.List;

import dao.general.DaoJugadorForaneo;

import modelo.JugadorForaneo;
import servicio.interfaz.IServicioJugadorForaneo;

public class ServicioJugadorForaneo implements IServicioJugadorForaneo {
	
	DaoJugadorForaneo daoJugadorForaneo;

	public DaoJugadorForaneo getDaoJugadorForaneo() {
		return daoJugadorForaneo;
	}

	public void setDaoJugadorForaneo(DaoJugadorForaneo daoJugadorForaneo) {
		this.daoJugadorForaneo = daoJugadorForaneo;
	}

	@Override
	public void eliminar(JugadorForaneo j) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(JugadorForaneo j) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<JugadorForaneo> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JugadorForaneo> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
