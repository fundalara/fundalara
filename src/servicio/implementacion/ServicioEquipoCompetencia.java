package servicio.implementacion;

import java.util.List;

import dao.general.DaoEquipoCompetencia;

import modelo.EquipoCompetencia;
import servicio.interfaz.IServicioEquipoCompetencia;

public class ServicioEquipoCompetencia implements IServicioEquipoCompetencia {
	
	DaoEquipoCompetencia daoEquipoCompetencia;

	public DaoEquipoCompetencia getDaoEquipoCompetencia() {
		return daoEquipoCompetencia;
	}

	public void setDaoEquipoCompetencia(DaoEquipoCompetencia daoEquipoCompetencia) {
		this.daoEquipoCompetencia = daoEquipoCompetencia;
	}

	@Override
	public void eliminar(EquipoCompetencia e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(EquipoCompetencia i) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EquipoCompetencia> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EquipoCompetencia> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
