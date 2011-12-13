package servicio.competencia;

import java.util.List;

import dao.competencia.DaoEstadoCompetencia;

import modelo.EstadoCompetencia;

public class ServicioEstadoCompetencia implements IServicioEstadoCompetencia {
    
	DaoEstadoCompetencia daoEstadoCompetencia;
	
	
	public DaoEstadoCompetencia getDaoEstadoCompetencia() {
		return daoEstadoCompetencia;
	}

	public void setDaoEstadoCompetencia(DaoEstadoCompetencia daoEstadoCompetencia) {
		this.daoEstadoCompetencia = daoEstadoCompetencia;
	}

	@Override
	public void eliminar(EstadoCompetencia ec) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(EstadoCompetencia ec) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(EstadoCompetencia ec) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EstadoCompetencia> buscarPorCodigo(EstadoCompetencia ec) {
		// TODO Auto-generated method stub
		return null;
	}

}
