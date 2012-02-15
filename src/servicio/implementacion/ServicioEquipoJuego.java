package servicio.implementacion;

import java.util.List;

import dao.general.DaoEquipoJuego;

import modelo.Competencia;
import modelo.EquipoCompetencia;
import modelo.EquipoJuego;
import servicio.interfaz.IServicioEquipoJuego;

public class ServicioEquipoJuego implements IServicioEquipoJuego {
	
	DaoEquipoJuego daoEquipoJuego;

	public DaoEquipoJuego getDaoEquipoJuego() {
		return daoEquipoJuego;
	}

	public void setDaoEquipoJuego(DaoEquipoJuego daoEquipoJuego) {
		this.daoEquipoJuego = daoEquipoJuego;
	}

	@Override
	public void eliminar(EquipoJuego e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(EquipoJuego e) {
		if (e.getCodigoEquipoJuego() == 0){
			int codigo = daoEquipoJuego.listar(EquipoJuego.class).size()+1;
			e.setCodigoEquipoJuego(codigo);
			e.setGanado('A');
			e.setCarrera(0);
			e.setError(0);
			e.setHit(0);
			e.setEstatus('A');			
		}
		daoEquipoJuego.guardar(e);

	}

	@Override
	public List<EquipoJuego> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EquipoJuego> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EquipoJuego> buscarJuegoporCompetencia(Competencia competencia,
			EquipoCompetencia equipoCompetencia) {
		// TODO Auto-generated method stub
		return null;
	}

}
