package servicio.implementacion;

import java.util.List;

import dao.general.DaoProveedorBanco;

import modelo.PersonalTipoNomina;
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
	public List<ProveedorBanco> listar() {
		return daoProveedorBanco.listar(ProveedorBanco.class);
	}

	@Override
	public List<ProveedorBanco> listarActivos() {
		return daoProveedorBanco.listarActivos(ProveedorBanco.class);
	}

	@Override
	public ProveedorBanco buscarPorCodigo (ProveedorBanco d) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<ProveedorBanco> listarPorProveedor(String s){
		return  daoProveedorBanco.listarPorProveedor(s);
	}

}
