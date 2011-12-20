package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioCompetencia;

import dao.general.DaoCompetencia;

import modelo.Competencia;
import modelo.Divisa;

public class ServicioCompetencia implements IServicioCompetencia {

	DaoCompetencia daoCompetencia;
	@Override
	public void eliminar(Competencia c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(Competencia c) {
		// TODO Auto-generated method stub

	}

	/*@Override
	public void actualizar(Competencia c) {
		// TODO Auto-generated method stub

	}*/

	/*@Override
	public Competencia buscarPorCodigo(Competencia c) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<Competencia> listar() {
		return daoCompetencia.listar(Competencia.class);
	}

	@Override
	public List<Competencia> listarActivos() {
		//return daoCompetencia.listarActivos(Competencia.class);
		return null;
	}

	public DaoCompetencia getDaoCompetencia() {
		return daoCompetencia;
	}

	public void setDaoCompetencia(DaoCompetencia daoCompetencia) {
		this.daoCompetencia = daoCompetencia;
	}

}
