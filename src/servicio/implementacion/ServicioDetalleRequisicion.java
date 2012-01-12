package servicio.implementacion;

import dao.general.DaoDetalleRequisicion;
import modelo.DetalleRequisicion;
import servicio.interfaz.IServicioDetalleRequisicion;

public class ServicioDetalleRequisicion implements IServicioDetalleRequisicion {

	DaoDetalleRequisicion daoDetalleRequisicion;
	
	@Override
	public void eliminar(DetalleRequisicion dr) {
		// TODO Auto-generated method stub
		daoDetalleRequisicion.eliminar(dr);
	}

	@Override
	public void agregar(DetalleRequisicion dr) {
		// TODO Auto-generated method stub
		daoDetalleRequisicion.guardar(dr);
	}

	@Override
	public void actualizar(DetalleRequisicion dr) {
		// TODO Auto-generated method stub
		daoDetalleRequisicion.actualizar(dr);
	}

	public DaoDetalleRequisicion getDaoDetalleRequisicion() {
		return daoDetalleRequisicion;
	}

	public void setDaoDetalleRequisicion(DaoDetalleRequisicion daoDetalleRequisicion) {
		this.daoDetalleRequisicion = daoDetalleRequisicion;
	}

	
	
}
