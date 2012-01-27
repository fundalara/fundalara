package servicio.implementacion;

import java.util.List;

import dao.general.DaoIngreso;

import modelo.EgresoFormaPago;
import modelo.Ingreso;
import servicio.interfaz.IServicioIngreso;

public class ServicioIngreso implements IServicioIngreso {
	
	DaoIngreso daoIngreso;
	public DaoIngreso getDaoIngreso() {
		return daoIngreso;
	}

	public void setDaoIngreso(DaoIngreso daoIngreso) {
		this.daoIngreso = daoIngreso;
	}

	@Override
	public void eliminar(Ingreso c) {
		daoIngreso.eliminar(c);

	}

	@Override
	public void agregar(Ingreso c) {
		daoIngreso.guardar(c);

	}

	@Override
	public void actualizar(Ingreso c) {
		daoIngreso.actualizar(c);

	}

	@Override
	public List<Ingreso> listar() {
		return daoIngreso.listar(Ingreso.class);
	}

	@Override
	public List<Ingreso> listarActivos() {
		return daoIngreso.listarActivos(Ingreso.class);
	}

	@Override
	public Ingreso buscarPorCodigo (Ingreso d) {
		// TODO Auto-generated method stub
		return null;
	}

}
