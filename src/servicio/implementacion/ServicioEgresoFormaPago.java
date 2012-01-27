package servicio.implementacion;

import java.util.List;

import dao.general.DaoEgresoFormaPago;

import modelo.EgresoCuentaPagar;
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
	public List<EgresoFormaPago> listar() {
		return daoEgresoFormaPago.listar(EgresoFormaPago.class);
	}

	@Override
	public List<EgresoFormaPago> listarActivos() {
		return daoEgresoFormaPago.listarActivos(EgresoFormaPago.class);
	}

	@Override
	public EgresoFormaPago buscarPorCodigo (EgresoFormaPago d) {
		// TODO Auto-generated method stub
		return null;
	}
}
