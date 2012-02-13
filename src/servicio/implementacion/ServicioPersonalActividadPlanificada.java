package servicio.implementacion;

import java.util.List;

import modelo.DatoBasico;
import modelo.Persona;
import modelo.Personal;
import modelo.PersonalActividadPlanificada;
import modelo.PlanificacionActividad;
import servicio.interfaz.IServicioPersonalActividadPlanificada;
import dao.general.DaoPersonalActividadPlanificada;

public class ServicioPersonalActividadPlanificada implements IServicioPersonalActividadPlanificada {

	DaoPersonalActividadPlanificada daoPersonalActividadPlanificada;

	@Override
	public void eliminar(PersonalActividadPlanificada pap) {
		daoPersonalActividadPlanificada.eliminar(pap);
	}

	@Override
	public void agregar(PersonalActividadPlanificada pap) {
		daoPersonalActividadPlanificada.guardar(pap);
	}

	@Override
	public void actualizar(PersonalActividadPlanificada pap) {
		daoPersonalActividadPlanificada.actualizar(pap);
	}

	public DaoPersonalActividadPlanificada getDaoPersonalActividadPlanificada() {
		return daoPersonalActividadPlanificada;
	}

	public void setDaoPersonalActividadPlanificada(DaoPersonalActividadPlanificada daoPersonalActividadPlanificada) {
		this.daoPersonalActividadPlanificada = daoPersonalActividadPlanificada;
	}

	@Override
	public List<PersonalActividadPlanificada> listarPersonalMant(DatoBasico tipoPersonal) {
		return daoPersonalActividadPlanificada.listarPersonalActividadPlanificada(tipoPersonal);
	}

	@Override
	public PersonalActividadPlanificada Buscar(Persona persona) {
		return this.daoPersonalActividadPlanificada.Buscar(persona.getCedulaRif());
	}

	@Override
	public List<PersonalActividadPlanificada> listar() {

		return daoPersonalActividadPlanificada.listar(PersonalActividadPlanificada.class);
	}

	@Override
	public PersonalActividadPlanificada BuscarPersonalActividad(Personal personal, PlanificacionActividad planificacionActividad) {
		return daoPersonalActividadPlanificada.buscarPersonalActividad(personal, planificacionActividad);
	}
}
