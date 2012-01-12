package servicio.implementacion;

import java.util.List;

import dao.general.DaoRepresentanteJugadorPlan;

import modelo.RepresentanteJugadorPlan;
import servicio.interfaz.IServicioRepresentanteJugadorPlan;

public class ServicioRepresentanteJugadorPlan implements
		IServicioRepresentanteJugadorPlan {

	
	DaoRepresentanteJugadorPlan daoRepresentanteJugadorPlan;
	
	
	public DaoRepresentanteJugadorPlan getDaoRepresentanteJugadorPlan() {
		return daoRepresentanteJugadorPlan;
	}

	public void setDaoRepresentanteJugadorPlan(
			DaoRepresentanteJugadorPlan daoRepresentanteJugadorPlan) {
		this.daoRepresentanteJugadorPlan = daoRepresentanteJugadorPlan;
	}

	@Override
	public void eliminar(RepresentanteJugadorPlan a) {
		daoRepresentanteJugadorPlan.eliminar(a);

	}

	@Override
	public void agregar(RepresentanteJugadorPlan a) {
		daoRepresentanteJugadorPlan.guardar(a);

	}

	@Override
	public void actualizar(RepresentanteJugadorPlan a) {
		daoRepresentanteJugadorPlan.actualizar(a);

	}

	@Override
	public List<RepresentanteJugadorPlan> listar() {
		return daoRepresentanteJugadorPlan.listar(RepresentanteJugadorPlan.class);
	}

}
