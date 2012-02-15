package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioHospedaje;

import dao.general.DaoHospedaje;

import modelo.Competencia;
import modelo.FamiliarJugador;
import modelo.Hospedaje;
import modelo.Representante;//NUEVO

/**
 * Clase para brindar los servicios para manejar los datos relacionados al hospedje
 * @author Erika O
 * @version 0.1 29/12/2011
 *
 */

public class ServicioHospedaje implements IServicioHospedaje {

	DaoHospedaje daoHospedaje;
	
	public DaoHospedaje getDaoHospedaje() {
		return daoHospedaje;
	}

	public void setDaoHospedaje(DaoHospedaje daoHospedaje) {
		this.daoHospedaje = daoHospedaje;
	}

	
	@Override
	public void eliminar(Hospedaje c) {
		daoHospedaje.eliminar(c);
	}

	@Override
	public void agregar(Hospedaje c) {
		c.setCodigoHospedaje(daoHospedaje.generarCodigo(c.getClass()));
		daoHospedaje.guardar(c);
	}

	@Override
	public void actualizar(Hospedaje c) {
		daoHospedaje.actualizar(c);
	}

	@Override
	public List<Hospedaje> listar() {
		return daoHospedaje.listar(Hospedaje.class);
	}

	public List<Hospedaje> listarrepre(FamiliarJugador familiarJugador) {
		return daoHospedaje.listarrepre(familiarJugador);
	}


	public boolean verificar(Competencia competencia,
			FamiliarJugador familiarJugador) {
		return daoHospedaje.verificar(competencia, familiarJugador);
	}


	public Hospedaje activar(Competencia competencia,
			FamiliarJugador familiarJugador) {
		return daoHospedaje.activar(competencia, familiarJugador);
	}

	@Override
	public Representante buscar(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
