package servicio.implementacion;

import java.util.List;

import dao.general.DaoActividadEntrenamiento;
import dao.general.DaoEscalaMedicion;

import modelo.EscalaMedicion;
import servicio.interfaz.IServicioEscalaMedicion;

public class ServicioEscalaMedicion implements IServicioEscalaMedicion {
	
	DaoEscalaMedicion daoEscalaMedicion;
	

	@Override
	public void guardar(EscalaMedicion em) {
		daoEscalaMedicion.guardar(em);

	}

	@Override
	public void actualizar(EscalaMedicion em) {
		daoEscalaMedicion.actualizar(em);

	}

	@Override
	public void eliminar(EscalaMedicion em) {
		daoEscalaMedicion.eliminar(em);

	}

	@Override
	public List<EscalaMedicion> listar() {
		return daoEscalaMedicion.listar(EscalaMedicion.class);
	}

	public DaoEscalaMedicion getDaoEscalaMedicion() {
		return daoEscalaMedicion;
	}

	public void setDaoEscalaMedicion(DaoEscalaMedicion daoEscalaMedicion) {
		this.daoEscalaMedicion = daoEscalaMedicion;
	}
	
	

}
