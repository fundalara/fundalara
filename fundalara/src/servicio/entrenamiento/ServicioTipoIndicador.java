package servicio.entrenamiento;

import java.util.List;

import dao.entrenamiento.DaoTipoIndicador;

import modelo.TipoIndicador;

public class ServicioTipoIndicador implements IServicioTipoIndicador {

	DaoTipoIndicador daoTipoIndicador;
	
	
	public DaoTipoIndicador getDaoTipoIndicador() {
		return daoTipoIndicador;
	}

	public void setDaoTipoIndicador(DaoTipoIndicador daoTipoIndicador) {
		this.daoTipoIndicador = daoTipoIndicador;
	}

	@Override
	public void eliminar(TipoIndicador ti) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(TipoIndicador ti) {
		daoTipoIndicador.guardar(ti);

	}

	@Override
	public void actualizar(TipoIndicador ti) {
		daoTipoIndicador.actualizar(ti);
	}

	@Override
	public List<TipoIndicador> listar() {
		List<TipoIndicador> ti = daoTipoIndicador.listar(new TipoIndicador());
		return ti;
	}

}
