package servicio.implementacion;

import java.util.List;

import dao.general.DaoActividad;
import modelo.Actividad;
import modelo.PlanificacionActividad;
import modelo.TareaActividad;
import servicio.interfaz.IServicioActividad;

public class ServicioActividad implements IServicioActividad {

	DaoActividad daoActividad;
	
	@Override
	public void eliminar(Actividad a) {
		// TODO Auto-generated method stub
		daoActividad.eliminar(a);
	}

	@Override
	public void agregar(Actividad a) {
		// TODO Auto-generated method stub
		daoActividad.guardar(a);
	}

	@Override
	public void actualizar(Actividad a) {
		// TODO Auto-generated method stub
		daoActividad.actualizar(a);
	}

	public Actividad buscarActividad(PlanificacionActividad a) {
		Actividad actividad = daoActividad.buscarActividad(a);
		return actividad;
	}
	@Override
	public Actividad Buscar(PlanificacionActividad a, Class<Actividad> class1) {
		
		return this.daoActividad.BuscarActividad(a, class1);
	}

	@Override
	public List<Actividad> listarActivos() {
		// TODO Auto-generated method stub
		return daoActividad.listarActivos(Actividad.class);
	}

	@Override
	public List<TareaActividad> listar(Actividad actividad) {
		return this.daoActividad.listar(actividad);
	}
	
	public DaoActividad getDaoActividad() {
		return daoActividad;
	}

	public void setDaoActividad(DaoActividad daoActividad) {
		this.daoActividad = daoActividad;
	}
	public List<TareaActividad> listar() {
		return this.daoActividad.listar(Actividad.class);
	}
	
}
