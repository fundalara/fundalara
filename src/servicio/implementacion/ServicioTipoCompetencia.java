package servicio.implementacion;

import java.util.List;

import dao.general.DaoTipoCompetencia;

import modelo.TipoCompetencia;
import servicio.interfaz.IServicioTipoCompetencia;

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
	public List<TipoCompetencia> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoCompetencia> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoCompetencia buscarPorCodigo(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

}
