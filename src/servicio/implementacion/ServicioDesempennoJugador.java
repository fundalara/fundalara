package servicio.implementacion;

import java.util.List;

import dao.general.DaoDesempennoJugador;

import modelo.ActividadEntrenamiento;
import modelo.DesempeoJugador;
import servicio.interfaz.IServicioDesempennoJugador;

public class ServicioDesempennoJugador implements IServicioDesempennoJugador {

	DaoDesempennoJugador daoDesempennoJugador;
	@Override
	public void guardar(DesempeoJugador ae) {
		daoDesempennoJugador.guardar(ae);

	}

	@Override
	public void actualizar(DesempeoJugador ae) {
		// TODO Auto-generated method stub
		daoDesempennoJugador.actualizar(ae);

	}

	@Override
	public void eliminar(DesempeoJugador ae) {
		// TODO Auto-generated method stub
		daoDesempennoJugador.eliminar(ae);

	}

	@Override
	public List<DesempeoJugador> listar() {
		// TODO Auto-generated method stub
		return daoDesempennoJugador.listar(DesempeoJugador.class);
	}

	public DaoDesempennoJugador getDaoDesempennoJugador() {
		return daoDesempennoJugador;
	}

	public void setDaoDesempennoJugador(DaoDesempennoJugador daoDesempennoJugador) {
		this.daoDesempennoJugador = daoDesempennoJugador;
	}

}
