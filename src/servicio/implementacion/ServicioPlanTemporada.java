package servicio.implementacion;

import java.util.List;

import dao.general.DaoPlanTemporada;

import modelo.Categoria;
import modelo.LapsoDeportivo;
import modelo.PlanTemporada;
import servicio.interfaz.IServicioPlanTemporada;

public class ServicioPlanTemporada implements IServicioPlanTemporada {
    DaoPlanTemporada daoPlanTemporada;


	public DaoPlanTemporada getDaoPlanTemporada() {
		return daoPlanTemporada;
	}

	public void setDaoPlanTemporada(DaoPlanTemporada daoPlanTemporada) {
		this.daoPlanTemporada = daoPlanTemporada;
	}



	@Override
	public int generarCodigo() {
		return daoPlanTemporada.generarCodigo(PlanTemporada.class);
	}

	@Override
	public List<PlanTemporada> listarActivos() {
		return (List<PlanTemporada>) daoPlanTemporada.listarUnCampo(
				PlanTemporada.class, "estatus", 'A');
	}
	
	@Override
	public List<PlanTemporada> buscarPorLapsoDeportivo(LapsoDeportivo ld) {
		return daoPlanTemporada.buscarPorLapsoDeportivo(ld);
	}
	
	
	///
	
	
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
	
	@Override
	public PlanTemporada buscarPorCategoriaLapDep(Categoria ct,LapsoDeportivo ld) {
		return daoPlanTemporada.buscarPorCategoriaLapsoDeportivo(ct, ld);
	}


}
