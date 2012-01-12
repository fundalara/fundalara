package servicio.implementacion;

import java.util.List;

import dao.general.DaoIngresoFormaPago;

import modelo.Egreso;
import modelo.IngresoFormaPago;
import servicio.interfaz.IServicioIngresoFormaPago;

public class ServicioIngresoFormaPago implements IServicioIngresoFormaPago {
	
	DaoIngresoFormaPago daoIngresoFormaPago;
	public DaoIngresoFormaPago getDaoIngresoFormaPago() {
		return daoIngresoFormaPago;
	}

	public void setDaoIngresoFormaPago(DaoIngresoFormaPago daoIngresoFormaPago) {
		this.daoIngresoFormaPago = daoIngresoFormaPago;
	}

	@Override
	public void eliminar(IngresoFormaPago c) {
		daoIngresoFormaPago.eliminar(c);

	}

	@Override
	public void agregar(IngresoFormaPago c) {
		daoIngresoFormaPago.guardar(c);

	}

	@Override
	public void actualizar(IngresoFormaPago c) {
		daoIngresoFormaPago.actualizar(c);

	}

	@Override
	public List<IngresoFormaPago> listar() {
		return daoIngresoFormaPago.listar(IngresoFormaPago.class);
	}

	@Override
	public List<IngresoFormaPago> listarActivos() {
		return daoIngresoFormaPago.listarActivos(IngresoFormaPago.class);
	}

	@Override
	public IngresoFormaPago buscarPorCodigo (IngresoFormaPago d) {
		// TODO Auto-generated method stub
		return null;
	}

}
