package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonal;

import modelo.Personal;
import servicio.interfaz.IServicioPersonal;

public class ServicioPersonal implements IServicioPersonal {
	
	DaoPersonal daoPersonal;
	public DaoPersonal getDaoPersonal() {
		return daoPersonal;
	}

	public void setDaoPersonal(DaoPersonal daoPersonal) {
		this.daoPersonal = daoPersonal;
	}

	@Override
	public void eliminar(Personal c) {
		daoPersonal.eliminar(c);

	}

	@Override
	public void agregar(Personal c) {
		daoPersonal.guardar(c);

	}

	@Override
	public void actualizar(Personal c) {
		daoPersonal.actualizar(c);

	}

	@Override
	public List listar() {
		// TODO Auto-generated method stub
		return daoPersonal.listar(new Personal());
	}

}
