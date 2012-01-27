package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioFamiliarJugador;

import dao.general.DaoFamiliarJugador;
import modelo.FamiliarJugador;
import modelo.Jugador;

public class ServicioFamiliarJugador implements IServicioFamiliarJugador {

	DaoFamiliarJugador daoFamiliarJugador;
	
	public DaoFamiliarJugador getDaoFamiliarJugador() {
		return daoFamiliarJugador;
	}

	public void setDaoFamiliarJugador(DaoFamiliarJugador daoFamiliarJugador) {
		this.daoFamiliarJugador = daoFamiliarJugador;
	}

	@Override
	public void eliminar(FamiliarJugador c) {
		daoFamiliarJugador.eliminar(c);

	}

	@Override
	public void agregar(FamiliarJugador c) {
		daoFamiliarJugador.guardar(c);

	}

	@Override
	public void actualizar(FamiliarJugador c) {
		daoFamiliarJugador.actualizar(c);

	}

	@Override
	public List<FamiliarJugador> listar() {
		return daoFamiliarJugador.listar( FamiliarJugador.class);
	}
	
	@Override
	public List<FamiliarJugador> buscarFamiliarJugador(Jugador jugador) {
		return daoFamiliarJugador.buscarFamiliarJugador(jugador);
	}

}
