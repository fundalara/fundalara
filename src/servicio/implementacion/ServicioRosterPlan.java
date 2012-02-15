package servicio.implementacion;

import java.util.List;

import dao.general.DaoRosterPlan;

import modelo.Equipo;
import modelo.JugadorPlan;
import modelo.RosterPlan;
import servicio.interfaz.IServicioRosterPlan;

public class ServicioRosterPlan implements IServicioRosterPlan {

	DaoRosterPlan daoRosterPlan;
	
	
	public DaoRosterPlan getDaoRosterPlan() {
		return daoRosterPlan;
	}

	public void setDaoRosterPlan(DaoRosterPlan daoRosterPlan) {
		this.daoRosterPlan = daoRosterPlan;
	}

	@Override
	public void eliminar(RosterPlan a) {
		daoRosterPlan.eliminar(a);

	}

	@Override
	public void agregar(RosterPlan a) {
		daoRosterPlan.guardar(a);

	}

	@Override
	public void actualizar(RosterPlan a) {
		daoRosterPlan.actualizar(a);

	}

	@Override
	public List<RosterPlan> listar() {
		return daoRosterPlan.listar(RosterPlan.class);
	}

	public RosterPlan buscar(JugadorPlan jugadorPlan){
		return daoRosterPlan.buscarRoster(jugadorPlan);
	}

	public void retirar(JugadorPlan jugador) {
		daoRosterPlan.retirarJugador(jugador);
	}
	
	public List<RosterPlan> listarPorEquipo(Equipo e){
		return daoRosterPlan.listarPorEquipo(e);
		
	}
	
}
