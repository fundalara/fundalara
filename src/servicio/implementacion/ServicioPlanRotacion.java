package servicio.implementacion;

import java.util.List;

import dao.general.DaoPlanRotacion;

import modelo.PlanRotacion;
import servicio.interfaz.IServicioPlanRotacion;

public class ServicioPlanRotacion implements IServicioPlanRotacion {

	DaoPlanRotacion daoPlanRotacion;
	
	public DaoPlanRotacion getDaoPlanRotacion() {
		return daoPlanRotacion;
	}

	public void setDaoPlanRotacion(DaoPlanRotacion daoPlanRotacion) {
		this.daoPlanRotacion = daoPlanRotacion;
	}

	@Override
	public void guardar(PlanRotacion pr) {
		daoPlanRotacion.guardar(pr);
	}

	@Override
	public void actualizar(PlanRotacion pr) {
		daoPlanRotacion.actualizar(pr);
	}

	@Override
	public void eliminar(PlanRotacion pr) {
		daoPlanRotacion.eliminar(pr);
	}

	@Override
	public List<PlanRotacion> listar() {
		return daoPlanRotacion.listar(PlanRotacion.class);
	}

}
