package servicio.implementacion;

import java.util.List;

import dao.general.DaoModalidadCompetencia;

import modelo.ModalidadCompetencia;
import servicio.interfaz.IServicioModalidadCompetencia;

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
	public List<ModalidadCompetencia> listar(ModalidadCompetencia mc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModalidadCompetencia> listarActivos(ModalidadCompetencia mc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModalidadCompetencia> buscarPorCodigo(ModalidadCompetencia mc) {
		// TODO Auto-generated method stub
		return null;
	}

}
