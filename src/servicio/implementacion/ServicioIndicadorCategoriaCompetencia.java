package servicio.implementacion;

import java.util.List;

import dao.general.DaoIndicadorCategoriaCompetencia;

import modelo.IndicadorCategoriaCompetencia;
import servicio.interfaz.IServicioIndicadorCategoriaCompetencia;

public class ServicioIndicadorCategoriaCompetencia implements
		IServicioIndicadorCategoriaCompetencia {
	
	public DaoIndicadorCategoriaCompetencia getDaoIndicadorCategoriaCompetencia() {
		return daoIndicadorCategoriaCompetencia;
	}

	public void setDaoIndicadorCategoriaCompetencia(
			DaoIndicadorCategoriaCompetencia daoIndicadorCategoriaCompetencia) {
		this.daoIndicadorCategoriaCompetencia = daoIndicadorCategoriaCompetencia;
	}

	DaoIndicadorCategoriaCompetencia daoIndicadorCategoriaCompetencia;

	@Override
	public void eliminar(IndicadorCategoriaCompetencia i) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(IndicadorCategoriaCompetencia i) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<IndicadorCategoriaCompetencia> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IndicadorCategoriaCompetencia> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
