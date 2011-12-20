package servicio.implementacion;

import java.util.List;

import dao.general.DaoValorEscala;

import modelo.ValorEscala;
import servicio.interfaz.IServicioValorEscala;

public class ServicioValorEscala implements IServicioValorEscala {
    DaoValorEscala daoValorEscala;
	@Override
	public void guardar(ValorEscala ve) {
		// TODO Auto-generated method stub
		daoValorEscala.guardar(ve);
	}

	@Override
	public void actualizar(ValorEscala ve) {
		// TODO Auto-generated method stub
		daoValorEscala.actualizar(ve);
	}

	@Override
	public void eliminar(ValorEscala ve) {
		// TODO Auto-generated method stub
		daoValorEscala.eliminar(ve);
	}

	@Override
	public List<ValorEscala> listar() {
		// TODO Auto-generated method stub
		return daoValorEscala.listar(ValorEscala.class);
	}

	public DaoValorEscala getDaoValorEscala() {
		return daoValorEscala;
	}

	public void setDaoValorEscala(DaoValorEscala daoValorEscala) {
		this.daoValorEscala = daoValorEscala;
	}

}
