package servicio.implementacion;

import java.util.List;

import dao.general.DaoCondicionCompetencia;

import modelo.CondicionCompetencia;
import modelo.DatoBasico;
import modelo.Roster;

import servicio.interfaz.IServicioCondicionCompetencia;


public class ServicioCondicionCompetencia implements IServicioCondicionCompetencia {

	DaoCondicionCompetencia daoCondicionCompetencia;
	
	
	
	public DaoCondicionCompetencia getDaoCondicionCompetencia() {
		return daoCondicionCompetencia;
	}

	public void setDaoCondicionCompetencia(
			DaoCondicionCompetencia daoCondicionCompetencia) {
		this.daoCondicionCompetencia = daoCondicionCompetencia;
	}

	@Override
	public void eliminar(CondicionCompetencia cc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregar(CondicionCompetencia cc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CondicionCompetencia> listar() {
		return daoCondicionCompetencia.listar( CondicionCompetencia.class);
	}

	@Override
	public List<CondicionCompetencia> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CondicionCompetencia> listarCondicion(DatoBasico cc) {
	    return null;
	}

}
