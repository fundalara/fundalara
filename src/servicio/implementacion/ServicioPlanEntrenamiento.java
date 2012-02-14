package servicio.implementacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import dao.general.DaoPlanEntrenamiento;

import modelo.PlanEntrenamiento;
import modelo.PlanTemporada;
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

	public void setDaoPlanEntrenamiento(
			DaoPlanEntrenamiento daoPlanEntrenamiento) {
		this.daoPlanEntrenamiento = daoPlanEntrenamiento;
	}

	public List<PlanEntrenamiento> listarActivos() {
		return daoPlanEntrenamiento.listarUnCampo(PlanEntrenamiento.class,
				"estatus", 'A');
	}

	public int generarCodigo() {
		return daoPlanEntrenamiento.generarCodigo(PlanEntrenamiento.class);
	}

	@Override
	public List<PlanEntrenamiento> buscarporPlanTemporada(PlanTemporada pt) {

		return daoPlanEntrenamiento.buscarporPlanTemporada(pt);
	}


}
