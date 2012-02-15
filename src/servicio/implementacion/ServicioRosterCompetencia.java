package servicio.implementacion;

import java.util.List;

import dao.general.DaoRosterCompetencia;

import modelo.Constante;
<<<<<<< HEAD
import modelo.JugadorForaneo;
=======
import modelo.Equipo;
>>>>>>> fdc8650d5d9f9268111948fd9cd27434d46dd314
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
	/*	if (c.getCodigoRosterCompetencia() == 0){
			   int cod = daoRosterCompetencia.listar(JugadorForaneo.class).size()+1;
			   c.setCodigoRosterCompetencia(cod);
			   c.setEstatus('A');
			}*/
		
//			c.setCodigoRosterCompetencia(c.getCodigoRosterCompetencia() + 1);
			c.setEstatus('A');
			daoRosterCompetencia.guardar(c);
	
	}

	@Override
	public List<RosterCompetencia> listarCompetenciasExistentes(int codcomp,int codequipo) {
		// TODO Auto-generated method stub
		return daoRosterCompetencia.listarRexistentes(codcomp, codequipo);
	}

	@Override
	public List<RosterCompetencia> listarActivos() {
		// TODO Auto-generated method stub
		return daoRosterCompetencia.listarActivos();
	}

	@Override
	public List<RosterCompetencia> listarJexistentes(int codcomp) {
		// TODO Auto-generated method stub
		return daoRosterCompetencia.listarJexistentes(codcomp);
	}

	@Override
	public void actualizar(RosterCompetencia c) {
		// TODO Auto-generated method stub
		c.setEstatus('A');
		System.out.println("ASIGNO EL STATUS");
		daoRosterCompetencia.actualizar(c);
		
	}

	@Override
	public List<RosterCompetencia> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RosterCompetencia> listarPorEquipo(Equipo e){
		return daoRosterCompetencia.listarPorEquipo(e);
		
	}
}
