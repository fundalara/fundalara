package servicio.implementacion;

import java.util.List;

import dao.general.DaoComisionActividad;
import modelo.Actividad;
import modelo.ComisionActividad;
import modelo.ComisionActividadPlanificada;
import servicio.interfaz.IServicioComisionActividad;

public class ServicioComisionActividad implements IServicioComisionActividad {

	DaoComisionActividad daoComisionActividad;
	
	
	public DaoComisionActividad getDaoComisionActividad() {
		return daoComisionActividad;
	}

	public void setDaoComisionActividad(DaoComisionActividad daoComisionActividad) {
		this.daoComisionActividad = daoComisionActividad;
	}

	@Override
	public void eliminar(ComisionActividad a) {
		// TODO Auto-generated method stub
		daoComisionActividad.eliminar(a);
	}

	@Override
	public void agregar(ComisionActividad a) {
		// TODO Auto-generated method stub
		daoComisionActividad.guardar(a);
	}

	@Override
	public void actualizar(ComisionActividad a) {
		// TODO Auto-generated method stub
		daoComisionActividad.actualizar(a);
	}

	@Override
	public List<ComisionActividad> listar() {
		return this.daoComisionActividad.listar(ComisionActividad.class);
	}

	@Override
	public List<ComisionActividad> listar(Actividad actividad) {
		return this.daoComisionActividad.listar(actividad);
	}

}
