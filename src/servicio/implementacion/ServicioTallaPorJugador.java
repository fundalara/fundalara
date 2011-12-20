package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioTallaPorJugador;

import dao.general.DaoTallaPorJugador;

import modelo.TallaPorJugador;

public class ServicioTallaPorJugador implements IServicioTallaPorJugador {

	DaoTallaPorJugador daoTallaPorJugador;
	
	public DaoTallaPorJugador getDaoTallaPorJugador() {
		return daoTallaPorJugador;
	}

	public void setDaoTallaPorJugador(DaoTallaPorJugador daoTallaPorJugador) {
		this.daoTallaPorJugador = daoTallaPorJugador;
	}

	@Override
	public void eliminar(TallaPorJugador c) {
		daoTallaPorJugador.eliminar(c);

	}

	@Override
	public void agregar(TallaPorJugador c) {
		daoTallaPorJugador.guardar(c);

	}

	@Override
	public void actualizar(TallaPorJugador c) {
		daoTallaPorJugador.actualizar(c);

	}

	@Override
	public List<TallaPorJugador> listar() {
		return daoTallaPorJugador.listar( TallaPorJugador.class);
	}

}
