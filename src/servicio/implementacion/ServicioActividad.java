package servicio.implementacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Actividad;
import modelo.PlanificacionActividad;
import modelo.TareaActividad;
import servicio.interfaz.IServicioActividad;

import comun.TipoDatoBasico;

import dao.general.DaoActividad;

public class ServicioActividad implements IServicioActividad {

	DaoActividad daoActividad;

	@Override
	public void eliminar(Actividad a) {
		daoActividad.eliminar(a);
	}

	@Override
	public void agregar(Actividad a) {
		daoActividad.guardar(a);
	}

	@Override
	public void actualizar(Actividad a) {
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

	public List<Actividad> listarMantenimientos(Date fechaInicio, Date fechaFin) {
		List<Actividad> lista1 = daoActividad.listarActivos(Actividad.class);
		List<Actividad> lista2 = new ArrayList<Actividad>();
		for (int i = 0; i < lista1.size(); i++) {
			if (lista1.get(i).getFechaInicio().after(fechaInicio)
					&& lista1.get(i).getFechaCulminacion().before(fechaFin)
					&& (lista1.get(i).getPlanificacionActividad().getDatoBasico().getDatoBasico().getCodigoDatoBasico() != TipoDatoBasico.ACTIVIDADES_COMPLEMENTARIAS
							.getCodigo())) {
				lista2.add(lista1.get(i));
			}
		}
		return lista2;
	}

	public List<Actividad> listarComplementarias(Date fechaInicio, Date fechaFin) {
		List<Actividad> lista1 = daoActividad.listarActivos(Actividad.class);
		List<Actividad> lista2 = new ArrayList<Actividad>();
		for (int i = 0; i < lista1.size(); i++) {
			if (lista1.get(i).getFechaInicio().after(fechaInicio)
					&& lista1.get(i).getFechaCulminacion().before(fechaFin)
					&& (lista1.get(i).getPlanificacionActividad().getDatoBasico().getDatoBasico().getCodigoDatoBasico() == TipoDatoBasico.ACTIVIDADES_COMPLEMENTARIAS
							.getCodigo())) {
				lista2.add(lista1.get(i));
			}
		}
		return lista2;
	}

	public List<Actividad> listarComplementarias() {
		List<Actividad> todas = daoActividad.listarActivos(Actividad.class);
		List<Actividad> complementarias = new ArrayList<Actividad>();
		for (int i = 0; i < todas.size(); i++) {
			if (todas.get(i).getPlanificacionActividad().getDatoBasico().getDatoBasico().getCodigoDatoBasico() == TipoDatoBasico.ACTIVIDADES_COMPLEMENTARIAS
					.getCodigo()) {
				complementarias.add(todas.get(i));
			}
		}
		return complementarias;
	}

}
