package servicio.implementacion;

import java.util.List;

import dao.general.DaoSesionEjecutada;

import modelo.SesionEjecutada;
import servicio.interfaz.IServicioSesionEjecutada;

public class ServicioSesionEjecutada implements IServicioSesionEjecutada{

	DaoSesionEjecutada daoSesionEjecutada;
	@Override
	public void guardar(SesionEjecutada se) {
		// TODO Auto-generated method stub
		daoSesionEjecutada.guardar(se);
	}

	@Override
	public void actualizar(SesionEjecutada se) {
		// TODO Auto-generated method stub
		daoSesionEjecutada.actualizar(se);
	}

	@Override
	public void eliminar(SesionEjecutada se) {
		// TODO Auto-generated method stub
		daoSesionEjecutada.eliminar(se);
	}

	@Override
	public List<SesionEjecutada> listar() {
		// TODO Auto-generated method stub
		return daoSesionEjecutada.listar(SesionEjecutada.class);
	}

	public DaoSesionEjecutada getDaoSesionEjecutada() {
		return daoSesionEjecutada;
	}

	public void setDaoSesionEjecutada(DaoSesionEjecutada daoSesionEjecutada) {
		this.daoSesionEjecutada = daoSesionEjecutada;
	}
	

}
