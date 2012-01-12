package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioDatoSocial;

import dao.general.DaoDatoSocial;
import modelo.DatoSocial;
import modelo.Jugador;

/**
 * Clase para brindar los servicios para manejar los datos relacionados con los datos sociales de los jugadores
 * @author Robert A
 * @author German L
 * @version 0.1 10/01/2012
 *
 */
public class ServicioDatoSocial implements IServicioDatoSocial {

	DaoDatoSocial daoDatoSocial;

	public DaoDatoSocial getDaoDatoSocial() {
		return daoDatoSocial;
	}

	public void setDaoDatoSocial(DaoDatoSocial daoDatoSocial) {
		this.daoDatoSocial = daoDatoSocial;
	}

	@Override
	public void eliminar(DatoSocial c) {
		daoDatoSocial.eliminar(c);

	}

	@Override
	public void agregar(DatoSocial c) {
		daoDatoSocial.guardar(c);

	}

	public void agregar(List<DatoSocial> datos) {
		daoDatoSocial.guardar(datos);

	}

	@Override
	public void actualizar(DatoSocial c) {
		daoDatoSocial.actualizar(c);

	}
	
	public void actualizar(List<DatoSocial> datos, Jugador jugador){
		daoDatoSocial.actualizar(datos, jugador);
	}

	@Override
	public List<DatoSocial> listar() {
		return daoDatoSocial.listar(DatoSocial.class);
	}

}
