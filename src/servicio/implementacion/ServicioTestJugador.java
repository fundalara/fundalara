package servicio.implementacion;

import java.util.List;

import dao.general.DaoTestJugador;

import modelo.TestJugador;
import servicio.interfaz.IServicioTestJugador;

public class ServicioTestJugador implements IServicioTestJugador {
	
	DaoTestJugador daoTestJugador;

	@Override
	public void guardar(TestJugador tj) {
		daoTestJugador.guardar(tj);

	}

	@Override
	public void actualizar(TestJugador tj) {
		daoTestJugador.actualizar(tj);

	}

	@Override
	public void eliminar(TestJugador tj) {
		daoTestJugador.eliminar(tj);

	}

	@Override
	public List<TestJugador> listar() {
		return daoTestJugador.listar(TestJugador.class);
	}

	public DaoTestJugador getDaoTestJugador() {
		return daoTestJugador;
	}

	public void setDaoTestJugador(DaoTestJugador daoTestJugador) {
		this.daoTestJugador = daoTestJugador;
	}
	
	

}
