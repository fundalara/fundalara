package servicio.implementacion;

import java.util.List;

import dao.general.DaoTipoIngreso;

import modelo.ProveedorBanco;
import modelo.TipoIngreso;
import servicio.interfaz.IServicioTipoIngreso;

public class ServicioTipoIngreso implements
		IServicioTipoIngreso {
	
	DaoTipoIngreso daoTipoIngreso;
	public DaoTipoIngreso getDaoTipoIngreso() {
		return daoTipoIngreso;
	}

	public void setDaoTipoIngreso(DaoTipoIngreso daoTipoIngreso) {
		this.daoTipoIngreso = daoTipoIngreso;
	}

	@Override
	
	
	public void eliminar(TipoIngreso c) {
		daoTipoIngreso.eliminar(c);

	}

	public DaoTipoIngreso getDaoTipoPagoRepresentante() {
		return daoTipoIngreso;
	}

	public void setDaoTipoPagoRepresentante(
			DaoTipoIngreso daoTipoPagoRepresentante) {
		this.daoTipoIngreso = daoTipoPagoRepresentante;
	}

	@Override
	public void agregar(TipoIngreso c) {
		daoTipoIngreso.guardar(c);

	}

	@Override
	public void actualizar(TipoIngreso c) {
		daoTipoIngreso.actualizar(c);

	}

	@Override
	public List<TipoIngreso> listar() {
		return daoTipoIngreso.listar(TipoIngreso.class);
	}

	@Override
	public List<TipoIngreso> listarActivos() {
		return daoTipoIngreso.listarActivos(TipoIngreso.class);
	}

	@Override
	public TipoIngreso buscarPorCodigo (TipoIngreso d) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public TipoIngreso buscarPorNombre(String td) {
		// TODO Auto-generated method stub
		return daoTipoIngreso.buscarPorNombre(td);
	}
	
	public List<TipoIngreso> listarCuotas(){
		return daoTipoIngreso.listarCuotas();
	}
	
}
