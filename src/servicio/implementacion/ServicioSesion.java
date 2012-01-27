package servicio.implementacion;

import java.util.List;

import dao.general.DaoSesion;

import modelo.Sesion;
import servicio.interfaz.IServicioSesion;

public class ServicioSesion implements IServicioSesion {

	DaoSesion daoSesion;
		
	public DaoSesion getDaoSesion() {
		return daoSesion;
	}

	public void setDaoSesion(DaoSesion daoSesion) {
		this.daoSesion = daoSesion;
	}

	@Override
	public void guardar(Sesion s) {
		daoSesion.guardar(s);
	}

	@Override
	public void actualizar(Sesion s) {
		daoSesion.actualizar(s);
	}

	@Override
	public void eliminar(Sesion s) {
		daoSesion.eliminar(s);
	}

	@Override
	public List<Sesion> listar() {
		return daoSesion.listar(Sesion.class);
	}

}
