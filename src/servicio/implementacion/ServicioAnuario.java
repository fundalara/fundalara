package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioAnuario;
import servicio.interfaz.IServicioAscenso;

import dao.general.DaoAnuario;
import dao.general.DaoAscenso;

import modelo.Anuario;
import modelo.Ascenso;
import modelo.Roster;

/**
 * Clase para brindar los servicios para manejar los datos relacionados con el anuario de los jugadores
 * @author Robert A
 * @author German L
 * @version 0.1 02/02/2012
 */
public class ServicioAnuario implements IServicioAnuario {

	DaoAnuario daoAnuario;

	public DaoAnuario getDaoAnuario() {
		return daoAnuario;
	}
	
	public void setDaoAnuario(DaoAnuario daoAnuario) {
		this.daoAnuario = daoAnuario;
	}
	
	@Override
	public void actualizar(Anuario a) {
		daoAnuario.actualizar(a);
	}
	
	@Override
	public void agregar(Anuario a) {
		daoAnuario.guardar(a);
	}
	
	@Override
	public void eliminar(Anuario a) {
		daoAnuario.eliminar(a);
	}
	
	@Override
	public List<Anuario> listar() {
		return daoAnuario.listar(Anuario.class);
	}

}