package servicio.entrenamiento;

import java.util.List;

import dao.entrenamiento.DaoTipoEscalaMedicion;

import modelo.TipoEscalaMedicion;

public class ServicioTipoEscalaMedicion implements IServicioTipoEscalaMedicion {

	DaoTipoEscalaMedicion daoTipoEscalaMedicion;

	public DaoTipoEscalaMedicion getDaoTipoEscalaMedicion() {
		return daoTipoEscalaMedicion;
	}

	public void setDaoTipoEscalaMedicion(DaoTipoEscalaMedicion daoTipoEscalaMedicion) {
		this.daoTipoEscalaMedicion = daoTipoEscalaMedicion;
	}

	@Override
	public void eliminar(TipoEscalaMedicion tem) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(TipoEscalaMedicion tem) {
		daoTipoEscalaMedicion.guardar(tem);

	}

	@Override
	public void actualizar(TipoEscalaMedicion tem) {
		daoTipoEscalaMedicion.actualizar(tem);
	}

	@Override
	public List<TipoEscalaMedicion> listar() {
		return daoTipoEscalaMedicion.listar(new TipoEscalaMedicion());
	}

}
