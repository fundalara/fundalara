package servicio.implementacion;

import java.util.List;

import dao.general.DaoIndicadorCategoriaCompetencia;

import modelo.Categoria;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.Indicador;
import modelo.IndicadorCategoriaCompetencia;
import modelo.Liga;
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
		daoIndicadorCategoriaCompetencia.guardar(i);

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

	@Override
	public List<IndicadorCategoriaCompetencia> listarIndicadoresIndividualesPorCategoria(Categoria c, Competencia comp, DatoBasico db) {
		return daoIndicadorCategoriaCompetencia.listarIndicadoresIndividualesPorCategoria(c, comp,db);
		
	}

	@Override
	public List<IndicadorCategoriaCompetencia> listarIndicadoresColectivosPorCategoria(
			Categoria c, Competencia comp, DatoBasico db) {
		
		return daoIndicadorCategoriaCompetencia.listarIndicadoresColectivosPorCategoria(c, comp, db);
	}
	 
	@Override
	public List<IndicadorCategoriaCompetencia> listarIndicadoresSencillosIndividuales(
			Categoria c, Competencia comp, DatoBasico modalidad) {
		return daoIndicadorCategoriaCompetencia.listarIndicadoresSencillosIndividuales(c, comp, modalidad);
	}

	@Override
	public List<IndicadorCategoriaCompetencia> listarCompetenciaIndicador(
			Indicador i) {
		// TODO Auto-generated method stub
		return daoIndicadorCategoriaCompetencia.listarCompetenciaIndicador(i);
	}
	
	
	
	
}