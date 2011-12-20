package servicio.implementacion;

import java.util.List;

import dao.general.DaoTipoPagoRepresentante;

import modelo.ProveedorBanco;
import modelo.TipoPagoRepresentante;
import servicio.interfaz.IServicioTipoPagoRepresentante;

public class ServicioTipoPagoRepresentante implements
		IServicioTipoPagoRepresentante {
	
	DaoTipoPagoRepresentante daoTipoPagoRepresentante;
	@Override
	
	
	public void eliminar(TipoPagoRepresentante c) {
		daoTipoPagoRepresentante.eliminar(c);

	}

	public DaoTipoPagoRepresentante getDaoTipoPagoRepresentante() {
		return daoTipoPagoRepresentante;
	}

	public void setDaoTipoPagoRepresentante(
			DaoTipoPagoRepresentante daoTipoPagoRepresentante) {
		this.daoTipoPagoRepresentante = daoTipoPagoRepresentante;
	}

	@Override
	public void agregar(TipoPagoRepresentante c) {
		daoTipoPagoRepresentante.guardar(c);

	}

	@Override
	public void actualizar(TipoPagoRepresentante c) {
		daoTipoPagoRepresentante.actualizar(c);

	}

	@Override
	public List<TipoPagoRepresentante> listar() {
		return daoTipoPagoRepresentante.listar(TipoPagoRepresentante.class);
	}

	@Override
	public List<TipoPagoRepresentante> listarActivos() {
		return daoTipoPagoRepresentante.listarActivos(TipoPagoRepresentante.class);
	}

	@Override
	public TipoPagoRepresentante buscarPorCodigo (TipoPagoRepresentante d) {
		// TODO Auto-generated method stub
		return null;
	}

}
