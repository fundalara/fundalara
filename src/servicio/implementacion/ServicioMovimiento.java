package servicio.implementacion;

import java.util.List;

import dao.general.DaoMovimiento;

import modelo.Movimiento;
import servicio.interfaz.IServicioMovimiento;

public class ServicioMovimiento implements IServicioMovimiento {
	
	DaoMovimiento daoMovimiento;
	public DaoMovimiento getDaoMovimiento() {
		return daoMovimiento;
	}

	public void setDaoMovimiento(DaoMovimiento daoMovimiento) {
		this.daoMovimiento = daoMovimiento;
	}

	@Override
	public void eliminar(Movimiento c) {
		 daoMovimiento.eliminar(c);

	}

	@Override
	public void agregar(Movimiento c) {
		 daoMovimiento.guardar(c);

	}

	@Override
	public void actualizar(Movimiento c) {
		 daoMovimiento.actualizar(c);

	}

	@Override
	public List listar() {
		// TODO Auto-generated method stub
		return  daoMovimiento.listar(new Movimiento());
	}

}
