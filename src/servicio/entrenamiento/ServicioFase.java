package servicio.entrenamiento;

import java.util.List;

import dao.entrenamiento.DaoFase;

import modelo.Fase;

public class ServicioFase implements IServicioFase {

	DaoFase daoFase;

	public DaoFase getDaoFase() {
		return daoFase;
	}

	public void setDaoFase(DaoFase daoFase) {
		this.daoFase = daoFase;
	}

	@Override
	public void eliminar(Fase f) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(Fase f) {
		daoFase.guardar(f);
	}

	@Override
	public void actualizar(Fase f) {
		daoFase.actualizar(f);
	}

	@Override
	public List<Fase> listar() {
		List<Fase> f = daoFase.listar(Fase.class);
		return f;
	}

}
