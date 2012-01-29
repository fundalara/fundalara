package servicio.interfaz;

import java.util.List;

import modelo.Actividad;
import modelo.DatoBasico;
import modelo.Persona;
import modelo.PersonalActividadPlanificada;

public interface IServicioPersonalActividadPlanificada {
	
	public abstract void eliminar(PersonalActividadPlanificada pap);
	
	public abstract void agregar(PersonalActividadPlanificada pap);
		
	public abstract void actualizar(PersonalActividadPlanificada pap);

	public abstract List<PersonalActividadPlanificada> listarPersonalMant(DatoBasico tipoPersonal);
		
	public abstract List<PersonalActividadPlanificada> listar();

	public abstract PersonalActividadPlanificada Buscar(Persona persona);



}
