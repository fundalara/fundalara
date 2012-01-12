package servicio.implementacion;

import java.util.List;
import dao.general.DaoRepresentantePlan;
import modelo.RepresentantePlan;
import servicio.interfaz.IServicioRepresentantePlan;

public class ServicioRepresentantePlan implements IServicioRepresentantePlan {

	DaoRepresentantePlan daoRepresentantePlan;
	
	
	public DaoRepresentantePlan getDaoRepresentantePlan() {
		return daoRepresentantePlan;
	}

	public void setDaoRepresentantePlan(DaoRepresentantePlan daoRepresentantePlan) {
		this.daoRepresentantePlan = daoRepresentantePlan;
	}
	

	@Override
	public void eliminar(RepresentantePlan a) {
		daoRepresentantePlan.eliminar(a);

	}

	@Override
	public void agregar(RepresentantePlan a) {
		daoRepresentantePlan.guardar(a);

	}

	@Override
	public void actualizar(RepresentantePlan a) {
		daoRepresentantePlan.actualizar(a);

	}

	@Override
	public List<RepresentantePlan> listar() {
		return daoRepresentantePlan.listar(RepresentantePlan.class);
	}

}
