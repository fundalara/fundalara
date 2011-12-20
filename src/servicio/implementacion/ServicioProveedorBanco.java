package servicio.implementacion;

import java.util.List;

import dao.general.DaoProveedorBanco;

import modelo.ProveedorBanco;
import servicio.interfaz.IServicioProveedorBanco;

public class ServicioProveedorBanco implements IServicioProveedorBanco {
	
	DaoProveedorBanco daoProveedorBanco;
	public DaoProveedorBanco getDaoProveedorBanco() {
		return daoProveedorBanco;
	}

	public void setDaoProveedorBanco(DaoProveedorBanco daoProveedorBanco) {
		this.daoProveedorBanco = daoProveedorBanco;
	}

	@Override
	public void eliminar(ProveedorBanco c) {
		daoProveedorBanco.eliminar(c);

	}

	@Override
	public void agregar(ProveedorBanco c) {
		daoProveedorBanco.guardar(c);

	}

	@Override
	public void actualizar(ProveedorBanco c) {
		daoProveedorBanco.actualizar(c);

	}

	@Override
	public List listar() {
		// TODO Auto-generated method stub
		return daoProveedorBanco.listar(new ProveedorBanco());
	}

}
