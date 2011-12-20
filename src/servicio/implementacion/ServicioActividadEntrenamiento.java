package servicio.implementacion;

import java.util.List;
import javax.management.Query;

import servicio.interfaz.IServicioActividadEntrenamiento;
import dao.general.DaoActividadEntrenamiento;
import modelo.ActividadEntrenamiento;
import modelo.Categoria;
import modelo.DatoBasico;

public class ServicioActividadEntrenamiento implements
		IServicioActividadEntrenamiento {

	DaoActividadEntrenamiento daoActividadEntrenamiento;

	public void eliminar(ActividadEntrenamiento a) {
		daoActividadEntrenamiento.eliminar(a);
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
				.listar(ActividadEntrenamiento.class);
		return a;
	}

	@Override
	public ActividadEntrenamiento buscarClaveForegn(Categoria c, DatoBasico f,
			String idActividad) {
		// TODO Auto-generated method stub
		return daoActividadEntrenamiento.buscarClaveForegn(c, f, idActividad);
	}

	@Override
	public List<ActividadEntrenamiento> buscarTodo(Categoria c, DatoBasico f) {
		// TODO Auto-generated method stub
		return daoActividadEntrenamiento.buscarTodo(c, f);
	}

	@Override
	public List<ActividadEntrenamiento> buscarPorCategoria(Categoria c) {
		// TODO Auto-generated method stub
		return daoActividadEntrenamiento.buscarPorCategoria(c);
	}

}
