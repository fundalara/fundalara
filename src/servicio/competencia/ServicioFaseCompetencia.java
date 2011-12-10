package servicio.competencia;

import java.util.List;

import dao.competencia.DaoFaseCompetencia;

import modelo.FaseCompetencia;

public class ServicioFaseCompetencia implements IServicioFaseCompetencia {
    
	
	DaoFaseCompetencia daoFaseCompetencia;
	
	
	public DaoFaseCompetencia getDaoFaseCompetencia() {
		return daoFaseCompetencia;
	}

	public void setDaoFaseCompetencia(DaoFaseCompetencia daoFaseCompetencia) {
		this.daoFaseCompetencia = daoFaseCompetencia;
	}

	@Override
	public void eliminar(FaseCompetencia fc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(FaseCompetencia fc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(FaseCompetencia fc) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FaseCompetencia> buscarPorCodigo(FaseCompetencia fc) {
		// TODO Auto-generated method stub
		return null;
	}

}
