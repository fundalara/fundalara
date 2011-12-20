package servicio.implementacion;

import java.util.List;

import dao.general.DaoJuego;

import modelo.Juego;
import servicio.interfaz.IServicioJuego;

public class ServicioJuego implements IServicioJuego {
	
	DaoJuego daoJuego;

	public DaoJuego getDaoJuego() {
		return daoJuego;
	}

	public void setDaoJuego(DaoJuego daoJuego) {
		this.daoJuego = daoJuego;
	}

	@Override
	public void eliminar(Juego j) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(Juego j) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Juego> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Juego> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
