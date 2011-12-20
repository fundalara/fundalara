package servicio.implementacion;

import java.util.List;

import dao.general.DaoEgresoFormaPago;

import modelo.EgresoFormaPago;
import servicio.interfaz.IServicioEgresoFormaPago;

public class ServicioEgresoFormaPago implements IServicioEgresoFormaPago {
	
	DaoEgresoFormaPago daoEgresoFormaPago;
	public DaoEgresoFormaPago getDaoEgresoFormaPago() {
		return daoEgresoFormaPago;
	}

	public void setDaoEgresoFormaPago(DaoEgresoFormaPago daoEgresoFormaPago) {
		this.daoEgresoFormaPago = daoEgresoFormaPago;
	}

	@Override
	public void eliminar(EgresoFormaPago c) {
		daoEgresoFormaPago.eliminar(c);

	}

	@Override
	public void agregar(EgresoFormaPago c) {
		daoEgresoFormaPago.guardar(c);

	}

	@Override
	public void actualizar(EgresoFormaPago c) {
		daoEgresoFormaPago.actualizar(c);

	}

	@Override
	public List listar() {
		// TODO Auto-generated method stub
		return daoEgresoFormaPago.listar(new EgresoFormaPago());
	}

}
