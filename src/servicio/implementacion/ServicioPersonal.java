package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonal;

import modelo.Personal;
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
	public List<Personal> listar() {
		return daoPersonal.listar(Personal.class);
	}

	@Override
	public List<Personal> listarActivos() {
		return daoPersonal.listarActivos(Personal.class);
	}

	@Override
	public Personal buscarPorCodigo (Personal d) {
		// TODO Auto-generated method stub
		return null;
	}

}
