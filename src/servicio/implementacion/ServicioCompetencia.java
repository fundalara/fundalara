package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioCompetencia;

import dao.general.DaoCompetencia;

import modelo.Competencia;
import modelo.Divisa;
import modelo.Juego;

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
		if (c.getCodigoCompetencia()==0){
			int cod = daoCompetencia.listar(Competencia.class).size()+1;
			c.setCodigoCompetencia(cod);
			c.setEstatus('A');
		}
		daoCompetencia.guardar(c);		

	}

	@Override
	public int obtenerCodigoCompetencia(){ return daoCompetencia.listar(Competencia.class).size();}	
	
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

}
