package servicio.interfaz;

import java.util.Date;
import java.util.List;

import modelo.Equipo;
import modelo.Personal;
import modelo.PersonalEquipo;
import modelo.PlanTemporada;

public interface IServicioPersonalEquipo {
	public abstract void guardar(PersonalEquipo pe);
	
	public abstract void actualizar(PersonalEquipo pe);
	
	public abstract void eliminar(PersonalEquipo pe);
	
	public abstract List<PersonalEquipo> listar();
	
	public abstract List<PersonalEquipo> listarActivos();
	
	public abstract int generarCodigo();
	
	public abstract List<PersonalEquipo> buscarEquipo(Personal p);
	
	public abstract List<PersonalEquipo> buscarPersonal(Equipo eq, Date fecha);
	
	public abstract List<PersonalEquipo> buscarPorPlanTemporada(PlanTemporada plan);
	
	public abstract PersonalEquipo burcarPorPersonal(Personal personal);
	
	public abstract List<PersonalEquipo> listarxequipo(int codigo);
}
