package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonaJuridica;

import modelo.Nomina;
import modelo.PersonaJuridica;
import servicio.interfaz.IServicioPersonaJuridica;

public class ServicioPersonaJuridica implements IServicioPersonaJuridica {
	
	DaoPersonaJuridica daoPersonaJuridica;
	public DaoPersonaJuridica getDaoPersonaJuridica() {
		return daoPersonaJuridica;
	}

	public void setDaoPersonaJuridica(DaoPersonaJuridica daoPersonaJuridica) {
		this.daoPersonaJuridica = daoPersonaJuridica;
	}

	@Override
	public void eliminar(PersonaJuridica c) {
		daoPersonaJuridica.eliminar(c);

	}

	@Override
	public void agregar(PersonaJuridica c) {
		daoPersonaJuridica.guardar(c);
	}

	@Override
	public void actualizar(PersonaJuridica c) {
		daoPersonaJuridica.actualizar(c);
		
	}

	@Override
	public List<PersonaJuridica> listar() {
		return daoPersonaJuridica.listar(PersonaJuridica.class);
	}

	@Override
	public List<PersonaJuridica> listarActivos() {
		return daoPersonaJuridica.listarActivos(PersonaJuridica.class);
	}

	@Override
	public PersonaJuridica buscarPorCodigo (PersonaJuridica d) {
		// TODO Auto-generated method stub
		return null;
	}

	public PersonaJuridica buscarPorCedulaRif(String s){
		return (PersonaJuridica) daoPersonaJuridica.buscarPorCedulaRif(s);
	}
}
