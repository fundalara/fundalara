package servicio.implementacion;

import dao.general.DaoMaterialActividad;
import modelo.MaterialActividad;
import servicio.interfaz.IServicioMaterialActividad;

public class ServicioMaterialActividad implements IServicioMaterialActividad {

	DaoMaterialActividad daoMaterialActividad;
	
	@Override
	public void eliminar(MaterialActividad ma) {
		// TODO Auto-generated method stub
		daoMaterialActividad.eliminar(ma);
	}

	@Override
	public void agregar(MaterialActividad ma) {
		// TODO Auto-generated method stub
		daoMaterialActividad.guardar(ma);
	}

	@Override
	public void actualizar(MaterialActividad ma) {
		// TODO Auto-generated method stub
		daoMaterialActividad.actualizar(ma);
	}

	public DaoMaterialActividad getDaoMaterialActividad() {
		return daoMaterialActividad;
	}

	public void setDaoMaterialActividad(DaoMaterialActividad daoMaterialActividad) {
		this.daoMaterialActividad = daoMaterialActividad;
	}

}
