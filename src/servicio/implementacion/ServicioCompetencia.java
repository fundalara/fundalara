package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioCompetencia;

import dao.general.DaoCompetencia;

import modelo.Competencia;
import modelo.DatoBasico;
import modelo.Divisa;
import modelo.Juego;
import modelo.LapsoDeportivo;

public class ServicioCompetencia implements IServicioCompetencia {

	DaoCompetencia daoCompetencia;

	@Override
	public void eliminar(Competencia c) {
		// TODO Auto-generated method stub
		c.setEstatus('E');
		daoCompetencia.eliminar(c);
	}

	@Override
	public void agregar(Competencia c) {
		// TODO Auto-generated method stub
		if (c.getCodigoCompetencia() == 0) {
			int cod = daoCompetencia.listar(Competencia.class).size() + 1;
			c.setCodigoCompetencia(cod);
			c.setEstatus('A');
		}
		daoCompetencia.guardar(c);

	}

	@Override
	public void actualizar(Competencia c) {
		// TODO Auto-generated method stub
		daoCompetencia.actualizar(c);
	}

	@Override
	public List<Competencia> listarPorfiltro(String dato, int estadoComp) {
		return daoCompetencia.listarCompetenciasPorFiltro(dato, estadoComp);
	}

	@Override
	public void aperturarClausurarcompetencia(Competencia c, DatoBasico datob) {
		// TODO Auto-generated method stub
		c.setDatoBasicoByCodigoEstadoCompetencia(datob);
		daoCompetencia.actualizar(c);
	}

	@Override
	public int obtenerCodigoCompetencia() {
		return daoCompetencia.listar(Competencia.class).size();
	}

	@Override
	public List<Competencia> listar() {
		return daoCompetencia.listar(Competencia.class);
	}

	@Override
	public List<Competencia> listarActivos() {

		return null;
	}

	public DaoCompetencia getDaoCompetencia() {
		return daoCompetencia;
	}

	public void setDaoCompetencia(DaoCompetencia daoCompetencia) {
		this.daoCompetencia = daoCompetencia;
	}

	@Override
	public List<Competencia> listarPorEstatus(int estatus) {
		return daoCompetencia.listarPorEstatus(estatus);
	}

	@Override
	public List<Competencia> listarRegistradasAperturadas() {
		return daoCompetencia.listarRegistradasAperturadas();
	}
	
	@Override
	public List<Competencia> listarAperturadasClausurada(){
		return daoCompetencia.listarAperturadasClausurada();
	}

	@Override
	public List<Competencia> listarPorfiltro(String dato) {
		return daoCompetencia.listarCompetenciasPorFiltro(dato);
	}
	
	@Override
	public List<Competencia> buscarCompetencias(LapsoDeportivo lapso,
			DatoBasico db, DatoBasico db1) {
		// TODO Auto-generated method stub
		return daoCompetencia.buscarCompetencias(lapso, db, db1);
	}
	
	@Override
	public List<Competencia> buscarCompetenciaPorNombre(String nombre_comp, LapsoDeportivo lapso) {
		return daoCompetencia.buscarCompetenciaPorNombre(nombre_comp, lapso);
	}
}
