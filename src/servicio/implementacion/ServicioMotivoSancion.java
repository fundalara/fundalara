package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioMotivoSancion;

import dao.general.DaoMotivoSancion;

import modelo.MotivoSancion;

public class ServicioMotivoSancion implements IServicioMotivoSancion {

	DaoMotivoSancion daoMotivoSancion;
	
	public DaoMotivoSancion getDaoMotivoSancion() {
		return daoMotivoSancion;
	}

	public void setDaoMotivoSancion(DaoMotivoSancion daoMotivoSancion) {
		this.daoMotivoSancion = daoMotivoSancion;
	}

	@Override
	public void eliminar(MotivoSancion c) {
		daoMotivoSancion.eliminar(c);

	}

	@Override
	public void agregar(MotivoSancion c) {
		daoMotivoSancion.guardar(c);

	}

	@Override
	public void actualizar(MotivoSancion c) {
		daoMotivoSancion.actualizar(c);

	}

	@Override
	public List<MotivoSancion> listar() {
		return daoMotivoSancion.listar( MotivoSancion.class);
	}

}
