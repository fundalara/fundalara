package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioLogroPorJugador;

import dao.general.DaoLogroPorJugador;

import modelo.LogroPorJugador;

public class ServicioLogroPorJugador implements IServicioLogroPorJugador {

	DaoLogroPorJugador daoLogroPorJugador;
	
	public DaoLogroPorJugador getDaoLogroPorJugador() {
		return daoLogroPorJugador;
	}

	public void setDaoLogroPorJugador(DaoLogroPorJugador daoLogroPorJugador) {
		this.daoLogroPorJugador = daoLogroPorJugador;
	}

	@Override
	public void eliminar(LogroPorJugador c) {
		daoLogroPorJugador.eliminar(c);

	}

	@Override
	public void agregar(LogroPorJugador c) {
		daoLogroPorJugador.guardar(c);

	}

	@Override
	public void actualizar(LogroPorJugador c) {
		daoLogroPorJugador.actualizar(c);

	}

	@Override
	public List<LogroPorJugador> listar() {
		return daoLogroPorJugador.listar( LogroPorJugador.class);
	}

}
