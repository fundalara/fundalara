package servicio.competencia;

import java.util.List;

import dao.competencia.DaoModalidadCompetencia;

import modelo.ModalidadCompetencia;

public class ServicioModalidadCompetencia implements
		IServicioModalidadCompetencia {
    
	DaoModalidadCompetencia daoModalidadCompetencia;
	
	
	public DaoModalidadCompetencia getDaoModalidadCompetencia() {
		return daoModalidadCompetencia;
	}

	public void setDaoModalidadCompetencia(
			DaoModalidadCompetencia daoModalidadCompetencia) {
		this.daoModalidadCompetencia = daoModalidadCompetencia;
	}

	@Override
	public void eliminar(ModalidadCompetencia mc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(ModalidadCompetencia mc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(ModalidadCompetencia mc) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ModalidadCompetencia> buscarPorCodigo(ModalidadCompetencia mc) {
		// TODO Auto-generated method stub
		return null;
	}

}
