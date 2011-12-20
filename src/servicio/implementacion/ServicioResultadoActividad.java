package servicio.implementacion;

import dao.general.DaoResultadoActividad;
import modelo.ResultadoActividad;
import servicio.interfaz.IServicioResultadoActividad;

public class ServicioResultadoActividad implements IServicioResultadoActividad {

	DaoResultadoActividad daoResultadoActividad;
	
	@Override
	public void eliminar(ResultadoActividad ra) {
		// TODO Auto-generated method stub
		daoResultadoActividad.eliminar(ra);
	}

	@Override
	public void agregar(ResultadoActividad ra) {
		// TODO Auto-generated method stub
		daoResultadoActividad.guardar(ra);
	}

	@Override
	public void actualizar(ResultadoActividad ra) {
		// TODO Auto-generated method stub
		daoResultadoActividad.actualizar(ra);
	}

	public DaoResultadoActividad getDaoResultadoActividad() {
		return daoResultadoActividad;
	}

	public void setDaoResultadoActividad(DaoResultadoActividad daoResultadoActividad) {
		this.daoResultadoActividad = daoResultadoActividad;
	}

}
