package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioRecaudoPorProceso;

import dao.general.DaoRecaudoPorProceso;

import modelo.RecaudoPorProceso;

public class ServicioRecaudoPorProceso implements IServicioRecaudoPorProceso {

	DaoRecaudoPorProceso daoRecaudoPorProceso;
	
	public DaoRecaudoPorProceso getDaoRecaudoPorProceso() {
		return daoRecaudoPorProceso;
	}

	public void setDaoRecaudoPorProceso(DaoRecaudoPorProceso daoRecaudoPorProceso) {
		this.daoRecaudoPorProceso = daoRecaudoPorProceso;
	}

	@Override
	public void eliminar(RecaudoPorProceso c) {
		daoRecaudoPorProceso.eliminar(c);

	}

	@Override
	public void agregar(RecaudoPorProceso c) {
		daoRecaudoPorProceso.guardar(c);

	}

	@Override
	public void actualizar(RecaudoPorProceso c) {
		daoRecaudoPorProceso.actualizar(c);

	}

	@Override
	public List<RecaudoPorProceso> listar() {
		return daoRecaudoPorProceso.listar( RecaudoPorProceso.class);
	}

}
