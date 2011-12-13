package servicio.competencia;

import java.util.List;

import dao.competencia.DaoTipoCompetencia;

import modelo.TipoCompetencia;

public class ServicioTipoCompetencia implements IServicioTipoCompetencia {
    
	
	DaoTipoCompetencia daoTipoCompetencia;
	
	public DaoTipoCompetencia getDaoTipoCompetencia() {
		return daoTipoCompetencia;
	}

	public void setDaoTipoCompetencia(DaoTipoCompetencia daoTipoCompetencia) {
		this.daoTipoCompetencia = daoTipoCompetencia;
	}

	@Override
	public void eliminar(TipoCompetencia tc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(TipoCompetencia tc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(TipoCompetencia tc) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TipoCompetencia> buscarPorCodigo(TipoCompetencia tc) {
		// TODO Auto-generated method stub
		return null;
	}

}
