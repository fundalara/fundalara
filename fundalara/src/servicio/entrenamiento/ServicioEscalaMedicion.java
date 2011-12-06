package servicio.entrenamiento;

import java.util.List;

import dao.entrenamiento.DaoEscalaMedicion;

import modelo.EscalaMedicion;

public class ServicioEscalaMedicion implements IServicioEscalaMedicion {

	DaoEscalaMedicion daoEscalaMedicion;

	public DaoEscalaMedicion getDaoEscalaMedicion() {
		return daoEscalaMedicion;
	}

	public void setDaoEscalaMedicion(DaoEscalaMedicion daoEscalaMedicion) {
		this.daoEscalaMedicion = daoEscalaMedicion;
	}

	@Override
	public void eliminar(EscalaMedicion e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(EscalaMedicion e) {
		daoEscalaMedicion.guardar(e);

	}

	@Override
	public void actualizar(EscalaMedicion e) {
		daoEscalaMedicion.actualizar(e);

	}

	@Override
	public List<EscalaMedicion> listar() {
		List<EscalaMedicion> e = daoEscalaMedicion.listar(new EscalaMedicion());
		return e;
	}

	@Override
	public List<EscalaMedicion> buscar(Character estatus) {
		return daoEscalaMedicion.filtro(estatus);
	}

}
