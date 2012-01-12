package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioValorEscala;
import dao.general.DaoValorEscala;
import modelo.EscalaMedicion;
import modelo.ValorEscala;

public class ServicioValorEscala implements
		IServicioValorEscala {

	DaoValorEscala daoValorEscala;
	
	public DaoValorEscala getDaoValorEscala() {
		return daoValorEscala;
	}

	public void setDaoValorEscala(
			DaoValorEscala daoValorEscala) {
		this.daoValorEscala = daoValorEscala;
	}

	@Override
	public void eliminar(ValorEscala vem) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(ValorEscala vem) {
		daoValorEscala.guardar(vem);

	}

	@Override
	public void actualizar(ValorEscala vem) {
		daoValorEscala.actualizar(vem);
	}

	@Override
	public List<ValorEscala> listar() {
		List<ValorEscala> vem = daoValorEscala.listar(ValorEscala.class);
		return vem;
	}

	@Override
	public List<ValorEscala> buscarValor(EscalaMedicion escala) {
		return daoValorEscala.buscarValores(escala);
	}

}
