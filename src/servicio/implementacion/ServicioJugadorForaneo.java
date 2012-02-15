package servicio.implementacion;

import java.util.List;

import dao.general.DaoJugadorForaneo;

import modelo.Divisa;
import modelo.Equipo;
import modelo.JugadorForaneo;

import servicio.interfaz.IServicioJugadorForaneo;

public class ServicioJugadorForaneo implements IServicioJugadorForaneo {
	
	DaoJugadorForaneo daoJugadorForaneo;

	public DaoJugadorForaneo getDaoJugadorForaneo() {
		return daoJugadorForaneo;
	}

	public void setDaoJugadorForaneo(DaoJugadorForaneo daoJugadorForaneo) {
		this.daoJugadorForaneo = daoJugadorForaneo;
	}

	@Override
	public void eliminar(JugadorForaneo j) {
		// TODO Auto-generated method stub
		j.setEstatus('E');
		daoJugadorForaneo.eliminar(j);
	}

	@Override
	public void agregar(JugadorForaneo j) {
		// TODO Auto-generated method stub
		if (j.getCodigoJugadorForaneo() == 0){
			   int cod = daoJugadorForaneo.listar(JugadorForaneo.class).size()+1;
			   j.setCodigoJugadorForaneo(cod);
			   j.setEstatus('A');
			}
		
			j.setCodigoJugadorForaneo(daoJugadorForaneo.listar(JugadorForaneo.class).size()+1);
			daoJugadorForaneo.guardar(j);
	
			
		
	}

	@Override
	public List<JugadorForaneo> listar() {
		// TODO Auto-generated method stub
		return daoJugadorForaneo.listar(JugadorForaneo.class);
		}

	@Override
	public List<JugadorForaneo> listarActivos() {
		// TODO Auto-generated method stub
		return daoJugadorForaneo.listarActivos();
	}
	
	@Override
	public List<JugadorForaneo> buscarJugadorForaneo(String ced) {
		return daoJugadorForaneo.buscarJugadorForaneo(ced);
	}
	
	public List<JugadorForaneo> listarJugadorForaneoPorCategoria(int codigo) {
		return daoJugadorForaneo.listarJugadorForaneoPorCategoria(JugadorForaneo.class, codigo);
	}
	public List<JugadorForaneo> listarJugadorForaneoPorFiltro(String dato){
		return daoJugadorForaneo.listarJugadorForaneoPorFiltro(dato);
	}
}