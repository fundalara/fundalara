package servicio.implementacion;

import java.util.List;

import dao.general.DaoTestEvaluativo;

import modelo.DesempeoJugador;
import modelo.TestEvaluativo;
import servicio.interfaz.IServicioTestEvaluativo;

public class ServicioTestEvaluativo implements IServicioTestEvaluativo {

	DaoTestEvaluativo daoTestEvaluativo;
	@Override
	public void guardar(TestEvaluativo ae) {
		// TODO Auto-generated method stub
		daoTestEvaluativo.guardar(ae);
	}

	@Override
	public void actualizar(TestEvaluativo ae) {
		// TODO Auto-generated method stub
		daoTestEvaluativo.actualizar(ae);
	}

	@Override
	public void eliminar(TestEvaluativo ae) {
		// TODO Auto-generated method stub
		daoTestEvaluativo.eliminar(ae);
	}

	@Override
	public List<TestEvaluativo> listar() {
		// TODO Auto-generated method stub
		return daoTestEvaluativo.listar(TestEvaluativo.class);
	}

	public DaoTestEvaluativo getDaoTestEvaluativo() {
		return daoTestEvaluativo;
	}

	public void setDaoTestEvaluativo(DaoTestEvaluativo daoTestEvaluativo) {
		this.daoTestEvaluativo = daoTestEvaluativo;
	}
	
	

}
