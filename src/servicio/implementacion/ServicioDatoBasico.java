package servicio.implementacion;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import comun.TipoDatoBasico;

import servicio.interfaz.IServicioDatoBasico;

import dao.general.DaoDatoBasico;

import modelo.DatoBasico;
import modelo.TipoDato;

public class ServicioDatoBasico implements IServicioDatoBasico {
	DaoDatoBasico daoDatoBasico;
	
	@Override
	public void eliminar(DatoBasico d) {
		
	}

	@Override
	public void agregar(DatoBasico d) {
		daoDatoBasico.guardar(d);

	}

	@Override
	public void actualizar(DatoBasico d) {
		daoDatoBasico.actualizar(d);
	}

	@Override
	public List<DatoBasico> listar() {
		return daoDatoBasico.listar(DatoBasico.class);
	}

	@Override
	public List<DatoBasico> buscarPorTipoDato(TipoDato td) {
		// TODO Auto-generated method stub
		return daoDatoBasico.buscarPorTipoDato(td);
	}
	


	public DaoDatoBasico getDaoDatoBasico() {
		return daoDatoBasico;
	}

	public void setDaoDatoBasico(DaoDatoBasico daoDatoBasico) {
		this.daoDatoBasico = daoDatoBasico;
	}

	@Override
	public DatoBasico buscarPorCodigo(Integer i) {
		// TODO Auto-generated method stub
		return daoDatoBasico.buscarPorCodigo(i);
	}	


	public List<DatoBasico> listarPorTipoDato(String s){
		return daoDatoBasico.listarPorTipoDeDato(s);	
	}
	
	public List<DatoBasico> listarPorPadre(String s,Integer i){
		return daoDatoBasico.listarPorPadre(s,i);
		}


	@Override
	public DatoBasico buscarPorCodigo(String td) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DatoBasico> listarEstados() {
		return daoDatoBasico.listarEstados();		
	}	
	
	@Override
	public List<DatoBasico> listarParroquias() {
		return daoDatoBasico.listarParroquias();
	}
	
	
	
	@Override
	public List<DatoBasico> listarMunicipios() {
		return daoDatoBasico.listarMunicipios();		
	}
	
	@Override
	public List<DatoBasico> listarMunicipiosPorEstados(DatoBasico db) {
		return daoDatoBasico.listarMunicipiosPorEstados(db);		
	}

	@Override
	public List<DatoBasico> listarParroquiasPorMunicipios(DatoBasico db) {
		return daoDatoBasico.listarParroquiasPorMunicipios(db);		
	}

	@Override
	public List<DatoBasico> buscar(TipoDatoBasico tipoDato) {
		return daoDatoBasico.buscar(tipoDato);
	}

	@Override
	public List<DatoBasico> buscarDatosPorRelacion(DatoBasico datoBasico) {
		return daoDatoBasico.buscarPorRelacion(datoBasico);
	}
	
	public DatoBasico buscarPorString(String s){
		return daoDatoBasico.buscarPorString(s);
	}

	
	@Override
	public DatoBasico buscarTipo(TipoDatoBasico tipoDato, String nombre) {
		return daoDatoBasico.buscarTipo(tipoDato, nombre);
	}
	
	@Override 
	public List<DatoBasico> listarOrganizacionCompetencia() {
		return daoDatoBasico.listarOrganizacionCompetencia();		
	}
	
}
