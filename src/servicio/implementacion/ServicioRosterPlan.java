package servicio.implementacion;

import java.util.List;

import dao.general.DaoRosterPlan;

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

}
