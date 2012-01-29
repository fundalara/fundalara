package servicio.implementacion;

import java.util.List;

import modelo.Actividad;
import modelo.PlanificacionActividad;
import dao.general.DaoPlanificacionActividad;
import servicio.interfaz.IServicioPlanificacionActividad;

public class ServicioPlanificacionActividad implements IServicioPlanificacionActividad {
	DaoPlanificacionActividad daoPlanificacionActividad;

	@Override
	public void eliminar(PlanificacionActividad pa) {
		// TODO Auto-generated method stub
		daoPlanificacionActividad.eliminar(pa);
	}

	@Override
	public void agregar(PlanificacionActividad pa) {
		// TODO Auto-generated method stub
		daoPlanificacionActividad.guardar(pa);
	}

	@Override
	public void actualizar(PlanificacionActividad pa) {
		// TODO Auto-generated method stub
		daoPlanificacionActividad.actualizar(pa);
	}

	public DaoPlanificacionActividad getDaoPlanificacionActividad() {
		return daoPlanificacionActividad;
	}

	public void setDaoPlanificacionActividad(
			DaoPlanificacionActividad daoPlanificacionActividad) {
		this.daoPlanificacionActividad = daoPlanificacionActividad;
	}

	@Override
	public List<PlanificacionActividad> buscarVigente() {
		List<PlanificacionActividad> a = daoPlanificacionActividad.listarVigente(Actividad.class);	
		return null;
	}
	
	@Override
	public List<PlanificacionActividad> listarPlantilla() {
		return daoPlanificacionActividad.listarPlantilla() ;
	}

	@Override
	public List<PlanificacionActividad> listarMantenimientos() {						
		return daoPlanificacionActividad.listarMantenimientos();
	}

	@Override
	public List<PlanificacionActividad> listarComplementarias() {
		// TODO Auto-generated method stub
		return daoPlanificacionActividad.listarComplementarias();
	}

	@Override
	public List<PlanificacionActividad> listarActivos() {
		// TODO Auto-generated method stub
		return daoPlanificacionActividad.listarActivos(PlanificacionActividad.class);
	}

	@Override
	public List<PlanificacionActividad> listar() {
		// TODO Auto-generated method stub
		return daoPlanificacionActividad.listar(PlanificacionActividad.class);
	}

}
