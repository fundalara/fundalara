package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioTallaPorIndumentaria;

import dao.general.DaoTallaPorIndumentaria;

import modelo.TallaPorIndumentaria;

public class ServicioTallaPorIndumentaria implements
		IServicioTallaPorIndumentaria {

	DaoTallaPorIndumentaria daoTallaPorIndumentaria;
	
	public DaoTallaPorIndumentaria getDaoTallaPorIndumentaria() {
		return daoTallaPorIndumentaria;
	}

	public void setDaoTallaPorIndumentaria(
			DaoTallaPorIndumentaria daoTallaPorIndumentaria) {
		this.daoTallaPorIndumentaria = daoTallaPorIndumentaria;
	}

	@Override
	public void eliminar(TallaPorIndumentaria c) {
		daoTallaPorIndumentaria.eliminar(c);

	}

	@Override
	public void agregar(TallaPorIndumentaria c) {
		daoTallaPorIndumentaria.guardar(c);
	}

	@Override
	public void actualizar(TallaPorIndumentaria c) {
		daoTallaPorIndumentaria.actualizar(c);

	}

	@Override
	public List<TallaPorIndumentaria> listar() {
		return daoTallaPorIndumentaria.listar( TallaPorIndumentaria.class);
	}

}
