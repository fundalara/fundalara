package servicio.implementacion;

import java.util.List;

import dao.general.DaoLineUp;

import modelo.Competencia;
import modelo.Equipo;
import modelo.Juego;
import modelo.LineUp;
import servicio.interfaz.IServicioLineUp;

public class ServicioLineUp implements IServicioLineUp {
	
	DaoLineUp daoLineUp;

	public DaoLineUp getDaoLineUp() {
		return daoLineUp;
	}

	public void setDaoLineUp(DaoLineUp daoLineUp) {
		this.daoLineUp = daoLineUp;
	}

	@Override
	public void eliminar(LineUp l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(LineUp l) {
		if (l.getCodigoLineUp() == 0){
			int codigo = daoLineUp.listar(LineUp.class).size()+1;
			l.setCodigoLineUp(codigo);
		}
		daoLineUp.guardar(l);
	}

	@Override
	public List<LineUp> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LineUp> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LineUp> listarPlanificados(Juego j,Equipo e) {
		
		return daoLineUp.listarPlanificados(j,e);
	}

	@Override
	public List<LineUp> listarDefinitivos(Juego j, Equipo e) {
		
		return daoLineUp.listarDefinivos(j, e);
	}

}
