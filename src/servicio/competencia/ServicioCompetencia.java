package servicio.competencia;

import java.util.List;

import dao.competencia.DaoCompetencia;

import modelo.Competencia;

public class ServicioCompetencia implements IServicioCompetencia {

	DaoCompetencia daoCompetencia;
	
	
	public DaoCompetencia getDaoCompetencia() {
		return daoCompetencia;
	}

	public void setDaoCompetencia(DaoCompetencia daoCompetencia) {
		this.daoCompetencia = daoCompetencia;
	}

	@Override
	public void eliminar(Competencia c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(Competencia c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(Competencia c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Competencia> buscarPorCodigo(Competencia c) {
		// TODO Auto-generated method stub
		return null;
	}

}
