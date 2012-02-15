package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioIndicador;

import dao.general.DaoIndicador;

import modelo.DatoBasico;
import modelo.Divisa;
import modelo.Indicador;

public class ServicioIndicador implements IServicioIndicador {

	DaoIndicador daoIndicador;
	@Override
	public void eliminar(Indicador i) {
		// TODO Auto-generated method stub
		daoIndicador.eliminar(i);
	}

	@Override
	public void agregar(Indicador i) {
		// TODO Auto-generated method stub
		daoIndicador.guardar(i);
	}

	@Override
	public void actualizar(Indicador i) {
		// TODO Auto-generated method stub
		daoIndicador.actualizar(i);
	}

	/*@Override
	public Indicador buscarPorCodigo(Indicador i) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<Indicador> listar() {
		return daoIndicador.listar(Indicador.class);
	}

	@Override
	public List<Indicador> listarActivos() {
		return daoIndicador.listarActivos(Indicador.class);
	}

	public DaoIndicador getDaoIndicador() {
		return daoIndicador;
	}

	public void setDaoIndicador(DaoIndicador daoIndicador) {
		this.daoIndicador = daoIndicador;
	}
	
	public int generarCodigo(){
		return daoIndicador.generarCodigo(Indicador.class);
	}
	
	public List<Indicador> buscarPorDatoBasico(DatoBasico datoBasico){
		return daoIndicador.buscarPorDatoBasico(datoBasico);
	}

	@Override
	public List<Indicador> listarIndicadorIndividualPorModalidad(DatoBasico db) {
		// TODO Auto-generated method stub
		return daoIndicador.listarIndicadorIndividualPorModalidad(db);
	}

	@Override
	public List<Indicador> listarIndicadorColectivoPorModalidad(DatoBasico db) {
		// TODO Auto-generated method stub
		return daoIndicador.listarIndicadorColectivoPorModalidad(db);
	}

	@Override
	public List<Indicador> listarIndicadoresPorFiltro(String dato) {
		return daoIndicador.listarIndicadoresPorFiltro(dato);
	}

	@Override
	public List<Indicador> listarActivosYSistema() {
		return daoIndicador.listarActivosYSistema(Indicador.class);
	}
	
	public List <Indicador> listarIndicadorPorModalidadyTipo (DatoBasico modalidad, String m){
		return daoIndicador.listarIndicadorPorModalidadyTipo(modalidad, m);
	}
}
