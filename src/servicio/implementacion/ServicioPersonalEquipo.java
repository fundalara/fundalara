package servicio.implementacion;

import java.util.Date;
import java.util.List;

import dao.general.DaoPersonalEquipo;

import modelo.Equipo;
import modelo.Horario;
import modelo.Personal;
import modelo.PersonalEquipo;
import modelo.PlanTemporada;
import servicio.interfaz.IServicioPersonalEquipo;

public class ServicioPersonalEquipo implements IServicioPersonalEquipo {

	DaoPersonalEquipo daoPersonalEquipo;
	
	public DaoPersonalEquipo getDaoPersonalEquipo() {
		return daoPersonalEquipo;
	}

	public void setDaoPersonalEquipo(DaoPersonalEquipo daoPersonalEquipo) {
		this.daoPersonalEquipo = daoPersonalEquipo;
	}

	@Override
	public void guardar(PersonalEquipo pe) {
		daoPersonalEquipo.guardar(pe);
	}

	@Override
	public void actualizar(PersonalEquipo pe) {
		daoPersonalEquipo.actualizar(pe);
	}

	@Override
	public void eliminar(PersonalEquipo pe) {
		daoPersonalEquipo.eliminar(pe);
	}

	@Override
	public List<PersonalEquipo> listar() {
		return daoPersonalEquipo.listar(PersonalEquipo.class);
	}

	@Override
	public int generarCodigo() {
		return daoPersonalEquipo.generarCodigo(PersonalEquipo.class);
	}
	
	@Override
	public List<PersonalEquipo> buscarEquipo(Personal p)
	{
		// TODO Auto-generated method stub
		return daoPersonalEquipo.buscarEquipo(p);
	}
	
	@Override
	public List<PersonalEquipo> listarActivos() {
		// TODO Auto-generated method stub
		return daoPersonalEquipo.listarUnCampo(PersonalEquipo.class,"estatus",'A');
	}
	
	@Override
	public List<PersonalEquipo> buscarPersonal(Equipo eq, Date fecha) {
		// TODO Auto-generated method stub
		//return daoPersonalEquipo.listarDosCampos(PersonalEquipo.class, "equipo", eq, "fechaInicio",fecha);
		return daoPersonalEquipo.buscarPersonal(eq, fecha);
	}
	
	public List<PersonalEquipo> buscarPorPlanTemporada(PlanTemporada plan){
		return  daoPersonalEquipo.listarUnCampo(PersonalEquipo.class, "planTemporada", plan);
	}
	
	public PersonalEquipo burcarPorPersonal(Personal personal){
		return (PersonalEquipo) daoPersonalEquipo.buscarUnCampo(PersonalEquipo.class, "personal", personal);
	}

	@Override
	public List<PersonalEquipo> listarxequipo(int codigo) {
		// TODO Auto-generated method stub
		return daoPersonalEquipo.listarPersonal(codigo);
	}
	
}
