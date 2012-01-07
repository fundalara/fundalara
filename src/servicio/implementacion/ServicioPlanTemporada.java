package servicio.implementacion;

import java.util.List;

import dao.general.DaoPlanTemporada;

import modelo.PlanTemporada;
import servicio.interfaz.IServicioPlanTemporada;

public class ServicioPlanTemporada implements IServicioPlanTemporada {
    DaoPlanTemporada daoPlanTemporada;
	@Override
	public void guardar(PlanTemporada pt) {
		// TODO Auto-generated method stub
		daoPlanTemporada.guardar(pt);
	}

	@Override
	public void actualizar(PlanTemporada pt) {
		// TODO Auto-generated method stub
		daoPlanTemporada.actualizar(pt);
	}

	@Override
	public void eliminar(PlanTemporada pt) {
		// TODO Auto-generated method stub
		daoPlanTemporada.eliminar(pt);
	}

	@Override
	public List<PlanTemporada> listar() {
		// TODO Auto-generated method stub
		return daoPlanTemporada.listar(PlanTemporada.class);
	}

	public DaoPlanTemporada getDaoPlanTemporada() {
		return daoPlanTemporada;
	}

	public void setDaoPlanTemporada(DaoPlanTemporada daoPlanTemporada) {
		this.daoPlanTemporada = daoPlanTemporada;
	}

}
