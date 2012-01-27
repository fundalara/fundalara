package servicio.implementacion;

import java.util.ArrayList;
import java.util.List;

import servicio.interfaz.IServicioRoster;

import dao.general.DaoRoster;

import modelo.Equipo;
import modelo.Jugador;
import modelo.Roster;

public class ServicioRoster implements IServicioRoster {
	
	DaoRoster daoRoster;
	
	public DaoRoster getDaoRoster() {
		return daoRoster;
	}

	public void setDaoRoster(DaoRoster daoRoster) {
		this.daoRoster = daoRoster;
	}

	@Override
	public void eliminar(Roster c) {
		daoRoster.eliminar(c);
	}

	@Override
	public void agregar(Roster c) {
		daoRoster.guardar(c);
	}

	@Override
	public void actualizar(Roster c) {
		daoRoster.actualizar(c);
	}

	@Override
	public List<Roster> listar() {
		return daoRoster.listar( Roster.class);
	}

	@Override
	public List<Jugador> buscarJugadores(Equipo equipo, String filtro2, String filtro3, String filtro4, String filtro1){
		List<Jugador> jugad = new ArrayList<Jugador>();
		List<Roster> rosters = new ArrayList<Roster>();
		rosters=daoRoster.buscarJugadores(equipo, filtro2, filtro3, filtro4, filtro1);
		for (int i = 0; i < rosters.size(); i++) {
			jugad.add(rosters.get(i).getJugador());
		}		
		return jugad;
	}
	
	@Override
	public int obtenerUltimoId() {
		return daoRoster.obtenerUltimoId();
	}
	
	@Override
	public Roster buscarRoster(String ced) {
		return daoRoster.buscarRoster(ced);
	}
	
	@Override
	public List<Roster> listar(int codigo) {
		// TODO Auto-generated method stub
		return daoRoster.listarCedxEquipo(Roster.class, codigo);
	}

}
