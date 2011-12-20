package servicio.implementacion;

import java.util.List;

import dao.general.DaoTipoPagoRepresentante;

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
	public List listar() {
		// TODO Auto-generated method stub
		return daoTipoPagoRepresentante.listar(new TipoPagoRepresentante());
	}

}
