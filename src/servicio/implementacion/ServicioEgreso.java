package servicio.implementacion;

import java.util.List;

import dao.general.DaoEgreso;

import modelo.DocumentoIndumentaria;
import modelo.Egreso;
import servicio.interfaz.IServicioEgreso;

public class ServicioEgreso implements IServicioEgreso {
	
	DaoEgreso daoEgreso;
	public DaoEgreso getDaoEgreso() {
		return daoEgreso;
	}

	public void setDaoEgreso(DaoEgreso daoEgreso) {
		this.daoEgreso = daoEgreso;
	}

	@Override
	public void eliminar(Egreso c) {
		daoEgreso.eliminar(c);

	}

	@Override
	public void agregar(Egreso c) {
		daoEgreso.guardar(c);

	}

	@Override
	public void actualizar(Egreso c) {
		daoEgreso.actualizar(c);

	}

	@Override
	public List<Egreso> listar() {
		return daoEgreso.listar(Egreso.class);
	}

	@Override
	public List<Egreso> listarActivos() {
		return daoEgreso.listarActivos(Egreso.class);
	}

	@Override
	public Egreso buscarPorCodigo (Egreso d) {
		// TODO Auto-generated method stub
		return null;
	}

}
