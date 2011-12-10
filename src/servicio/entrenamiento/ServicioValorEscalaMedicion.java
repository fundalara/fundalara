package servicio.entrenamiento;

import java.util.List;

import dao.entrenamiento.DaoValorEscalaMedicion;

import modelo.ValorEscalaMedicion;

public class ServicioValorEscalaMedicion implements
		IServicionValorEscalaMedicion {

	DaoValorEscalaMedicion daoValorEscalaMedicion;
	
	public DaoValorEscalaMedicion getDaoValorEscalaMedicion() {
		return daoValorEscalaMedicion;
	}

	public void setDaoValorEscalaMedicion(
			DaoValorEscalaMedicion daoValorEscalaMedicion) {
		this.daoValorEscalaMedicion = daoValorEscalaMedicion;
	}

	@Override
	public void eliminar(ValorEscalaMedicion vem) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(ValorEscalaMedicion vem) {
		daoValorEscalaMedicion.guardar(vem);

	}

	@Override
	public void actualizar(ValorEscalaMedicion vem) {
		daoValorEscalaMedicion.actualizar(vem);
	}

	@Override
	public List<ValorEscalaMedicion> listar() {
		List<ValorEscalaMedicion> vem = daoValorEscalaMedicion
				.listar(new ValorEscalaMedicion());
		return vem;
	}

}
