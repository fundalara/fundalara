package servicio.implementacion;

import java.util.List;

import modelo.ClasificacionCompetencia;
import modelo.DatoBasico;
import servicio.interfaz.IServicioClasificacionCompetencia;
import dao.general.DaoClasificacionCompetencia;

public class ServicioClasificacionCompetencia implements
		IServicioClasificacionCompetencia {
	
	DaoClasificacionCompetencia daoClasificacionCompetencia;
	
	

	public DaoClasificacionCompetencia getDaoClasificacionCompetencia() {
		return daoClasificacionCompetencia;
	}

	public void setDaoClasificacionCompetencia(
			DaoClasificacionCompetencia daoClasificacionCompetencia) {
		this.daoClasificacionCompetencia = daoClasificacionCompetencia;
	}

	@Override
	public void eliminar(ClasificacionCompetencia cfc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(ClasificacionCompetencia cfc) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ClasificacionCompetencia> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClasificacionCompetencia> listarActivos() {
		// TODO Auto-generated method stub
		return daoClasificacionCompetencia.listarActivos();
	}

	@Override
	public List<ClasificacionCompetencia> listarClasificacion(DatoBasico d) {
		return daoClasificacionCompetencia.listarClasificacion(d);
	}

}
