package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonalActividad;
import modelo.Persona;
import modelo.PersonalActividad;
import servicio.interfaz.IServicioPersonalActividad;

public class ServicioPersonalActividad implements IServicioPersonalActividad {

	DaoPersonalActividad daoPersonalActividad;
	
	@Override
	public void eliminar(PersonalActividad pa) {
		// TODO Auto-generated method stub
		daoPersonalActividad.eliminar(pa);
	}

	@Override
	public void agregar(PersonalActividad pa) {
		// TODO Auto-generated method stub
		daoPersonalActividad.guardar(pa);
	}

	@Override
	public void actualizar(PersonalActividad pa) {
		// TODO Auto-generated method stub
		daoPersonalActividad.actualizar(pa);
	}

	public DaoPersonalActividad getDaoPersonalActividad() {
		return daoPersonalActividad;
	}

	public void setDaoPersonalActividad(DaoPersonalActividad daoPersonalActividad) {
		this.daoPersonalActividad = daoPersonalActividad;
	}

	@Override
	public List<PersonalActividad> listar() {
		return this.daoPersonalActividad.listar(PersonalActividad.class);
	}

	@Override
	public PersonalActividad Buscar(Persona persona) {
		
		return this.daoPersonalActividad.Buscar(persona.getCedulaRif());
	}

}
