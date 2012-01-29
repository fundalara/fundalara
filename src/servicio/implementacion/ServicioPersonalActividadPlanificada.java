package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonalActividadPlanificada;
import modelo.Actividad;
import modelo.DatoBasico;
import modelo.Persona;
import modelo.PersonalActividadPlanificada;
import servicio.interfaz.IServicioPersonalActividadPlanificada;

public class ServicioPersonalActividadPlanificada implements
		IServicioPersonalActividadPlanificada {

	DaoPersonalActividadPlanificada daoPersonalActividadPlanificada;

	@Override
	public void eliminar(PersonalActividadPlanificada pap) {
		// TODO Auto-generated method stub
		daoPersonalActividadPlanificada.eliminar(pap);
	}

	@Override
	public void agregar(PersonalActividadPlanificada pap) {
		// TODO Auto-generated method stub
		daoPersonalActividadPlanificada.guardar(pap);
	}

	@Override
	public void actualizar(PersonalActividadPlanificada pap) {
		// TODO Auto-generated method stub
		daoPersonalActividadPlanificada.actualizar(pap);
	}

	public DaoPersonalActividadPlanificada getDaoPersonalActividadPlanificada() {
		return daoPersonalActividadPlanificada;
	}

	public void setDaoPersonalActividadPlanificada(
			DaoPersonalActividadPlanificada daoPersonalActividadPlanificada) {
		this.daoPersonalActividadPlanificada = daoPersonalActividadPlanificada;
	}

	

	@Override
	public List<PersonalActividadPlanificada> listarPersonalMant(
			DatoBasico tipoPersonal) {
		// TODO Auto-generated method stub
		return daoPersonalActividadPlanificada.listarPersonalActividadPlanificada(tipoPersonal);
	}

	@Override
	public PersonalActividadPlanificada Buscar(Persona persona) {
		// TODO Auto-generated method stub
		return this.daoPersonalActividadPlanificada.Buscar(persona.getCedulaRif());
	}

	@Override
	public List<PersonalActividadPlanificada> listar() {
		
		return daoPersonalActividadPlanificada.listar(PersonalActividadPlanificada.class);
	}
}
