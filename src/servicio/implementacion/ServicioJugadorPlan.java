package servicio.implementacion;

import java.util.List;

import dao.general.DaoJugadorPlan;

import modelo.JugadorPlan;
import servicio.interfaz.IServicioJugadorPlan;

public class ServicioJugadorPlan implements IServicioJugadorPlan {

	DaoJugadorPlan daoJugadorPlan;
	
	public DaoJugadorPlan getDaoJugadorPlan() {
		return daoJugadorPlan;
	}

	public void setDaoJugadorPlan(DaoJugadorPlan daoJugadorPlan) {
		this.daoJugadorPlan = daoJugadorPlan;
	}

	@Override
	public void eliminar(JugadorPlan a) {
		daoJugadorPlan.eliminar(a);

	}

	@Override
	public void agregar(JugadorPlan a) {
		daoJugadorPlan.guardar(a);

	}

	@Override
	public void actualizar(JugadorPlan a) {
		daoJugadorPlan.actualizar(a);

	}

	@Override
	public List<JugadorPlan> listar() {
		return daoJugadorPlan.listar(JugadorPlan.class);
	}

}
