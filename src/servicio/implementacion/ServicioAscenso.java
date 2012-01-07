package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioAscenso;

import dao.general.DaoAscenso;

import modelo.Ascenso;

public class ServicioAscenso implements IServicioAscenso {
	
	DaoAscenso daoAscenso;
	
	
	public DaoAscenso getDaoAscenso() {
		return daoAscenso;
	}

	public void setDaoAscenso(DaoAscenso daoAscenso) {
		this.daoAscenso = daoAscenso;
	}

	@Override
	public void eliminar(Ascenso c) {
		daoAscenso.eliminar(c);

	}

	@Override
	public void agregar(Ascenso c) {
		daoAscenso.guardar(c);

	}

	@Override
	public void actualizar(Ascenso c) {
		daoAscenso.actualizar(c);

	}

	@Override
	public List<Ascenso> listar() {
		return daoAscenso.listar(Ascenso.class);
	}

}
