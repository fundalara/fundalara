package servicio.implementacion;

import java.util.List;

import dao.general.DaoRosterCompetencia;

import modelo.Constante;
import modelo.RosterCompetencia;
import servicio.interfaz.IServicioConstante;
import servicio.interfaz.IServicioRosterCompetencia;

public class ServicioRosterCompetencia implements IServicioRosterCompetencia {
	
	DaoRosterCompetencia daoRosterCompetencia; 

	public DaoRosterCompetencia getDaoRosterCompetencia() {
		return daoRosterCompetencia;
	}

	public void setDaoRosterCompetencia(DaoRosterCompetencia daoRosterCompetencia) {
		this.daoRosterCompetencia = daoRosterCompetencia;
	}

	@Override
	public void eliminar(RosterCompetencia c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(RosterCompetencia c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RosterCompetencia> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RosterCompetencia> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
