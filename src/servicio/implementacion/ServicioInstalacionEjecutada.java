package servicio.implementacion;

import java.util.List;

import dao.general.DaoInstalacionEjecutada;

import modelo.InstalacionEjecutada;
import servicio.interfaz.IServicioInstalacionEjecutada;

public class ServicioInstalacionEjecutada implements
		IServicioInstalacionEjecutada {
	DaoInstalacionEjecutada daoInstalacionEjecutada;
	
	@Override
	public void eliminar(InstalacionEjecutada instalacion) {
		// TODO Auto-generated method stub
		daoInstalacionEjecutada.eliminar(instalacion);
	}

	@Override
	public void guardar(InstalacionEjecutada instalacion) {
		// TODO Auto-generated method stub
		daoInstalacionEjecutada.guardar(instalacion);
	}

	@Override
	public void actualizar(InstalacionEjecutada instalacion) {
		// TODO Auto-generated method stub
		daoInstalacionEjecutada.guardar(instalacion);
	}

	@Override
	public List<InstalacionEjecutada> listarInstalacionEjecutada() {
		// TODO Auto-generated method stub
		return daoInstalacionEjecutada.listar(InstalacionEjecutada.class);
	}

	public DaoInstalacionEjecutada getDaoInstalacionEjecutada() {
		return daoInstalacionEjecutada;
	}

	public void setDaoInstalacionEjecutada(
			DaoInstalacionEjecutada daoInstalacionEjecutada) {
		this.daoInstalacionEjecutada = daoInstalacionEjecutada;
	}
	
}
