package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioDesempennoJugador;

import dao.general.DaoDesempennoJugador;
import modelo.ActividadEntrenamiento;
import modelo.DesempennoJugador;


public class ServicioDesempennoJugador implements IServicioDesempennoJugador {

	DaoDesempennoJugador daoDesempennoJugador;
	public DaoDesempennoJugador getDaoDesempennoJugador() {
		return daoDesempennoJugador;
	}

	public void setDaoDesempennoJugador(DaoDesempennoJugador daoDesempennoJugador) {
		this.daoDesempennoJugador = daoDesempennoJugador;
	}

	@Override
	public void guardar(DesempennoJugador ae) {
		// TODO Auto-generated method stub
		daoDesempennoJugador.guardar(ae);
		
	}

	@Override
	public void actualizar(DesempennoJugador ae) {
		// TODO Auto-generated method stub
		daoDesempennoJugador.actualizar(ae);
	}

	@Override
	public void eliminar(DesempennoJugador ae) {
		// TODO Auto-generated method stub
		daoDesempennoJugador.eliminar(ae);
	}

	@Override
	public List<DesempennoJugador> listar() {
		// TODO Auto-generated method stub
		return daoDesempennoJugador.listarActivos(DesempennoJugador.class);		
	}

}
