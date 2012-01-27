package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioAfeccionJugador;

import dao.general.DaoAfeccionJugador;
import modelo.AfeccionJugador;
import modelo.DatoMedico;

/**
 * Clase para brindar los servicios para manejar los datos relacionados con las afecciones de los jugadores
 * @author Robert A
 * @author German L
 * @version 0.1 29/12/2011
 *
 */
public class ServicioAfeccionJugador implements IServicioAfeccionJugador {

	DaoAfeccionJugador daoAfeccionJugador;

	public DaoAfeccionJugador getDaoAfeccionJugador() {
		return daoAfeccionJugador;
	}

	public void setDaoAfeccionJugador(DaoAfeccionJugador daoAfeccionJugador) {
		this.daoAfeccionJugador = daoAfeccionJugador;
	}

	@Override
	public void eliminar(AfeccionJugador c) {
		daoAfeccionJugador.eliminar(c);

	}

	@Override
	public void agregar(AfeccionJugador c) {
		daoAfeccionJugador.guardar(c);

	}

	@Override
	public void actualizar(AfeccionJugador c) {
		daoAfeccionJugador.actualizar(c);

	}
	
	public void actualizar(List<AfeccionJugador> afecciones, DatoMedico datoMedico) {
		daoAfeccionJugador.actualizar(afecciones,datoMedico);

	}

	@Override
	public List<AfeccionJugador> listar() {
		return daoAfeccionJugador.listar(AfeccionJugador.class);
	}

	public void agregar(List<AfeccionJugador> afecciones) {
		daoAfeccionJugador.guardar(afecciones);

	}

}
