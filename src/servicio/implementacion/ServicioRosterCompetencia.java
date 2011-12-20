package servicio.implementacion;

import java.util.List;

import dao.general.DaoRosterCompetencia;

import modelo.Constante;
import servicio.interfaz.IServicioConstante;

public class ServicioRosterCompetencia implements IServicioConstante {
	
	DaoRosterCompetencia daoRosterCompetencia; 

	public DaoRosterCompetencia getDaoRosterCompetencia() {
		return daoRosterCompetencia;
	}

	public void setDaoRosterCompetencia(DaoRosterCompetencia daoRosterCompetencia) {
		this.daoRosterCompetencia = daoRosterCompetencia;
	}

	@Override
	public void eliminar(Constante c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(Constante c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Constante> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Constante> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
