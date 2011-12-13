package servicio.competencia;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import dao.competencia.DaoEstadoVenezuela;
import dao.prueba.GenericDao;

import modelo.EstadoVenezuela;

public class ServicioEstadoVenezuela implements IServicioEstadoVenezuela {

    DaoEstadoVenezuela daoEstadoVenezuela;
    
    

	public DaoEstadoVenezuela getDaoEstadoVenezuela() {
		return daoEstadoVenezuela;
	}

	public void setDaoEstadoVenezuela(DaoEstadoVenezuela daoEstadoVenezuela) {
		this.daoEstadoVenezuela = daoEstadoVenezuela;
	}

	@Override
	public void eliminar(EstadoVenezuela e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(EstadoVenezuela e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(EstadoVenezuela e) {
		// TODO Auto-generated method stub

	}

	@Override
	public EstadoVenezuela buscarPorCodigo(EstadoVenezuela e) {
		return null;
	}

	@Override
	public List<EstadoVenezuela> listar() {		
		return daoEstadoVenezuela.listar(EstadoVenezuela.class);
	}

}
