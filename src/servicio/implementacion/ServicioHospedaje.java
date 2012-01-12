package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioHospedaje;

import dao.general.DaoHospedaje;

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
	
	@Override
	public Representante buscar (String id) {
		return daoHospedaje.buscar(id);
	}

}
