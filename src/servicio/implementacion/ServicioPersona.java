package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersona;

import modelo.Persona;
import servicio.interfaz.IServicioPersona;

public class ServicioPersona implements IServicioPersona {
	DaoPersona daoPersona;
	public DaoPersona getDaoPersona() {
		return daoPersona;
	}

	public void setDaoPersona(DaoPersona daoPersona) {
		this.daoPersona = daoPersona;
	}

	@Override
	public void eliminar(Persona c) {
		daoPersona.eliminar(c);

	}

	@Override
	public void agregar(Persona c) {
		daoPersona.guardar(c);

	}

	@Override
	public void actualizar(Persona c) {
		daoPersona.actualizar(c);

	}

	@Override
	public List listar() {
		// TODO Auto-generated method stub
		return daoPersona.listar(new Persona());
	}

}
