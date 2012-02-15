package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioFamiliarJugador;

import dao.general.DaoFamiliarJugador;
import modelo.DatoBasico;
import modelo.Familiar;
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

	
	
	public List<FamiliarJugador> listarPorRepresentante(Familiar familiar) {
		return daoFamiliarJugador.buscarPorRepresentante(familiar);
	}


	////
	
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
		return daoFamiliarJugador.listar(FamiliarJugador.class);
	}

	@Override
	public List<FamiliarJugador> buscarFamiliarJugador(Jugador jugador) {
		return daoFamiliarJugador.buscarFamiliarJugador(jugador);
	}

	@Override
	public FamiliarJugador buscarFamiliar(Familiar familiar) {
		return daoFamiliarJugador.buscar(familiar);
	}

	public void agregar(List<FamiliarJugador> familiaresJugadores,
			Jugador jugador) {
		daoFamiliarJugador.guardar(familiaresJugadores, jugador);
	}

	@Override
	public DatoBasico buscarParentesco(Familiar familiar, String cedulaJugador) {
		return daoFamiliarJugador.buscarParentesco(familiar, cedulaJugador);
	}
	
	public boolean esRepresentanteActual(String cedulaJugador, Familiar familiar){
		return daoFamiliarJugador.esRepresentanteActual( cedulaJugador,  familiar);
	}
	
	
	public Familiar buscarRepresentanteActual(String cedulaJugador) {
		return daoFamiliarJugador.buscarRepresentanteActual(cedulaJugador);
	}
	
	public FamiliarJugador buscarRepresentante(Jugador jugador){
		return daoFamiliarJugador.buscarRepresentante(jugador);
	}
}
