package servicio.implementacion;

import java.util.List;

import dao.general.DaoCondicionCompetencia;

import modelo.ClasificacionCompetencia;
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
		cc.setEstatus('E');
		daoCondicionCompetencia.eliminar(cc);
			
	}
	
	
	@Override
	public void agregar (CondicionCompetencia cc) {
		if (cc.getCodigoCondicionCompetencia() == 0){
			   int cod = daoCondicionCompetencia.listar(CondicionCompetencia.class).size()+1;
			   cc.setCodigoCondicionCompetencia(cod);
			   cc.setEstatus('A');
			}
			daoCondicionCompetencia.guardar(cc);	
			
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
	
	@Override
	public List<CondicionCompetencia> listarCondicionSeleccionada(ClasificacionCompetencia i){
		return daoCondicionCompetencia.listarCondicionSeleccionada(i);
	}

	public List<CondicionCompetencia> listarCondicion(DatoBasico cc) {
	    return null;
	}

	
	
}