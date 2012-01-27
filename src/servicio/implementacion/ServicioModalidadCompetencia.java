package servicio.implementacion;

import java.util.List;

import dao.general.DaoModalidadCompetencia;

import modelo.ModalidadCompetencia;
import modelo.TipoCompetencia;
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
	
	@Override
	public List<ModalidadCompetencia> listarPorTipoCompetencia(TipoCompetencia tc) {
		return  daoModalidadCompetencia.listarPorTipoCompetencia2(tc);
	}
	
	@Override
	public List<ModalidadCompetencia> listarModalidad (TipoCompetencia tc){
		return daoModalidadCompetencia.listarModalidad(tc);
	}

}
