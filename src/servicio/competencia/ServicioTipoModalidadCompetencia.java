package servicio.competencia;

import java.util.List;

import dao.competencia.DaoTipoModalidadCompetencia;

import modelo.TipoCompetencia;
import modelo.TipoModalidadCompetencia;

public class ServicioTipoModalidadCompetencia implements
		IServicioTipoModalidadCompetencia, IServicioTipoCompetencia {
    
	
	DaoTipoModalidadCompetencia daoTipoModalidadCompetencia;
	
	public DaoTipoModalidadCompetencia getDaoTipoModalidadCompetencia() {
		return daoTipoModalidadCompetencia;
	}

	public void setDaoTipoModalidadCompetencia(
			DaoTipoModalidadCompetencia daoTipoModalidadCompetencia) {
		this.daoTipoModalidadCompetencia = daoTipoModalidadCompetencia;
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

	@Override
	public void eliminar(TipoModalidadCompetencia t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(TipoModalidadCompetencia t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(TipoModalidadCompetencia t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TipoModalidadCompetencia> buscarPorCodigo(
			TipoModalidadCompetencia t) {
		// TODO Auto-generated method stub
		return null;
	}

}
