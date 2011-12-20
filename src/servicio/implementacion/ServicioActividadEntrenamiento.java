package servicio.implementacion;

import java.util.List;

import dao.general.DaoActividadEntrenamiento;

import modelo.ActividadEntrenamiento;
import servicio.interfaz.IServicioActividadEntrenamiento;

public class ServicioActividadEntrenamiento implements
		IServicioActividadEntrenamiento {

	DaoActividadEntrenamiento daoActividadEntrenamiento;

	public DaoActividadEntrenamiento getDaoActividadEntrenamiento() {
		return daoActividadEntrenamiento;
	}

	public void setDaoActividadEntrenamiento(
			DaoActividadEntrenamiento daoActividadEntrenamiento) {
		this.daoActividadEntrenamiento = daoActividadEntrenamiento;
	}

	@Override
	public void guardar(ActividadEntrenamiento ae) {
		daoActividadEntrenamiento.guardar(ae);
	}

	@Override
	public void actualizar(ActividadEntrenamiento ae) {
		daoActividadEntrenamiento.actualizar(ae);
	}

	@Override
	public void eliminar(ActividadEntrenamiento ae) {
		daoActividadEntrenamiento.eliminar(ae);
	}

	@Override
	public List<ActividadEntrenamiento> listar() {
		return daoActividadEntrenamiento.listar(ActividadEntrenamiento.class);
	}

}
