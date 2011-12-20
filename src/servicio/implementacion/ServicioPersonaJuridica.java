package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonaJuridica;

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
	public List listar() {
		// TODO Auto-generated method stub
		return daoPersonaJuridica.listar(new PersonaJuridica());
	}

}
