package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioEscalaMedicion;

import dao.general.DaoEscalaMedicion;

import modelo.EscalaMedicion;

public class ServicioEscalaMedicion implements IServicioEscalaMedicion {

	DaoEscalaMedicion daoEscalaMedicion;

	public DaoEscalaMedicion getDaoEscalaMedicion() {
		return daoEscalaMedicion;
	}

	public void setDaoEscalaMedicion(DaoEscalaMedicion daoEscalaMedicion) {
		this.daoEscalaMedicion = daoEscalaMedicion;
	}

	@Override
	public void eliminar(EscalaMedicion e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(EscalaMedicion e) {
		daoEscalaMedicion.guardar(e);

	}

	@Override
	public void actualizar(EscalaMedicion e) {
		daoEscalaMedicion.actualizar(e);

	}

	@Override
	public List<EscalaMedicion> listar() {
		
		return  daoEscalaMedicion.listar(EscalaMedicion.class);
	}

	@Override
	public EscalaMedicion buscar(String codigo) {
		return daoEscalaMedicion.buscarCodigo(codigo);
		
	}

	

}
