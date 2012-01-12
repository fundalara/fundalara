package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioDatoAcademico;

import dao.general.DaoDatoAcademico;
import modelo.DatoAcademico;

/**
 * Clase para brindar los servicios para manejar los datos relacionados con los datos academicos de los jugadores
 * @author Robert A
 * @author German L
 * @version 0.1 29/12/2011
 *
 */
public class ServicioDatoAcademico implements IServicioDatoAcademico {

	DaoDatoAcademico daoDatoAcademico;
	
	
	public DaoDatoAcademico getDaoDatoAcademico() {
		return daoDatoAcademico;
	}

	public void setDaoDatoAcademico(DaoDatoAcademico daoDatoAcademico) {
		this.daoDatoAcademico = daoDatoAcademico;
	}

	@Override
	public void eliminar(DatoAcademico c) {
		daoDatoAcademico.eliminar(c);

	}

	@Override
	public void agregar(DatoAcademico c) {
		daoDatoAcademico.guardar(c);

	}

	@Override
	public void actualizar(DatoAcademico c) {
		daoDatoAcademico.actualizar(c);

	}

	@Override
	public List<DatoAcademico> listar() {
		return daoDatoAcademico.listar( DatoAcademico.class);
	}

	@Override
	public int obtenerUltimoId() {
		return daoDatoAcademico.obtenerUltimoId();
	}
}
