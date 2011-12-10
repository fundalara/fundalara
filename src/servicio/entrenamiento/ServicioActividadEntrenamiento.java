package servicio.entrenamiento;

import java.util.List;

import javax.management.Query;

import dao.entrenamiento.DaoActividadEntrenamiento;

import modelo.ActividadEntrenamiento;

public class ServicioActividadEntrenamiento implements
		IServicioActividadEntrenamiento {

	DaoActividadEntrenamiento daoActividadEntrenamiento;

	public void eliminar(ActividadEntrenamiento a) {

	}

	public void agregar(ActividadEntrenamiento a) {
		daoActividadEntrenamiento.guardar(a);

	}

	public void actualizar(ActividadEntrenamiento a) {
		daoActividadEntrenamiento.actualizar(a);
	}

	public DaoActividadEntrenamiento getDaoActividadEntrenamiento() {
		return daoActividadEntrenamiento;
	}

	public void setDaoActividadEntrenamiento(
			DaoActividadEntrenamiento daoActividadEntrenamiento) {
		this.daoActividadEntrenamiento = daoActividadEntrenamiento;
	}

	public List<ActividadEntrenamiento> listar() {
		List<ActividadEntrenamiento> a = daoActividadEntrenamiento
				.listar(new ActividadEntrenamiento());
		return a;
	}

}
