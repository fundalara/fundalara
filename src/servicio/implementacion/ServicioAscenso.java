package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioAscenso;

import dao.general.DaoAscenso;

import modelo.Ascenso;
import modelo.Roster;

/**
 * Clase para brindar los servicios para manejar los datos relacionados con los ascensos de los jugadores
 * @author Robert A
 * @author German L
 * @version 0.1 29/12/2011
 *
 */
public class ServicioAscenso implements IServicioAscenso {

	DaoAscenso daoAscenso;

	public DaoAscenso getDaoAscenso() {
		return daoAscenso;
	}

	public void setDaoAscenso(DaoAscenso daoAscenso) {
		this.daoAscenso = daoAscenso;
	}

	@Override
	public void eliminar(Ascenso c) {
		daoAscenso.eliminar(c);

	}

	@Override
	public void agregar(Ascenso c) {
		daoAscenso.guardar(c);

	}

	@Override
	public void actualizar(Ascenso c) {
		daoAscenso.actualizar(c);

	}

	@Override
	public List<Ascenso> listar() {
		return daoAscenso.listar(Ascenso.class);
	}

	@Override
	public void actualizarAscenso(Roster r) {
		daoAscenso.actualizar(r);
	}

}
