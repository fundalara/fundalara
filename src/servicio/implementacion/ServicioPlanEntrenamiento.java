package servicio.implementacion;

import java.util.List;

import dao.general.DaoPlanEntrenamiento;

import modelo.PlanEntrenamiento;
import servicio.interfaz.IServicioPlanEntrenamiento;

public class ServicioPlanEntrenamiento implements IServicioPlanEntrenamiento {
	
	DaoPlanEntrenamiento daoPlanEntrenamiento;
	@Override
	public void guardar(PlanEntrenamiento pe) {
		daoPlanEntrenamiento.guardar(pe);

	}

	@Override
	public void actualizar(PlanEntrenamiento pe) {
		daoPlanEntrenamiento.actualizar(pe);

	}

	@Override
	public void eliminar(PlanEntrenamiento pe) {
		daoPlanEntrenamiento.eliminar(pe);

	}

	@Override
	public List<PlanEntrenamiento> listar() {
		return daoPlanEntrenamiento.listar(PlanEntrenamiento.class);
	}

	public DaoPlanEntrenamiento getDaoPlanEntrenamiento() {
		return daoPlanEntrenamiento;
	}

	public void setDaoPlanEntrenamiento(DaoPlanEntrenamiento daoPlanEntrenamiento) {
		this.daoPlanEntrenamiento = daoPlanEntrenamiento;
	}
	
	

}
