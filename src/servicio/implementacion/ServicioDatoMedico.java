package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioDatoMedico;

import dao.general.DaoDatoMedico;
import modelo.DatoMedico;

/**
 * Clase para brindar los servicios para manejar los datos relacionados con los datos medicos de los jugadores
 * @author Robert A
 * @author German L
 * @version 0.1 29/12/2011
 *
 */
public class ServicioDatoMedico implements IServicioDatoMedico {

	DaoDatoMedico daoDatoMedico;

	public DaoDatoMedico getDaoDatoMedico() {
		return daoDatoMedico;
	}

	public void setDaoDatoMedico(DaoDatoMedico daoDatoMedico) {
		this.daoDatoMedico = daoDatoMedico;
	}

	@Override
	public void eliminar(DatoMedico c) {
		daoDatoMedico.eliminar(c);

	}

	@Override
	public void agregar(DatoMedico c) {

		daoDatoMedico.guardar(c);

	}

	@Override
	public void actualizar(DatoMedico c) {
		daoDatoMedico.actualizar(c);

	}

	@Override
	public List<DatoMedico> listar() {
		return daoDatoMedico.listar(DatoMedico.class);
	}

	@Override
	public int obtenerUltimoId() {
		return daoDatoMedico.obtenerUltimoId();
	}
}