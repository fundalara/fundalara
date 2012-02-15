package servicio.implementacion;

import java.util.ArrayList;
import java.util.List;

import modelo.Persona;
import modelo.Personal;
import modelo.PersonalCargo;
import servicio.interfaz.IServicioPersona;
import dao.general.DaoPersona;

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
	public List<Persona> listar() {
		return daoPersona.listar(Persona.class);
	}

	@Override
	public List<Persona> listarActivos() {
		return daoPersona.listarActivos(Persona.class);
	}

	@Override
	public Persona buscarPorCodigo(Persona d) {
		return null;
	}

	public Persona buscarPorCedulaRif(String s) {
		return daoPersona.buscarPorCedulaRif(s);
	}

	public Persona buscarPorTipoPersona(String s, Integer i) {
		return daoPersona.buscarPorTipoPersona(s, i);
	}

	@Override
	public List<Persona> listarPorTipos(String tipoPersona) {
		return daoPersona.listarPorTipo(tipoPersona);
	}

	@Override
	public Persona buscarPorCodigo(String Cedula) {
		return this.daoPersona.buscarPorCedulaRif(Cedula);
	}

	@Override
	public List<Persona> listarTrabajadoresMantenimiento() {
		List<Persona> a = new ArrayList<Persona>();

		List<Persona> b = new ArrayList<Persona>();

		List<PersonalCargo> c = daoPersona.listar(PersonalCargo.class);
		b = daoPersona.listarActivos(Persona.class);

		for (PersonalCargo personalCargo : c) {
			if (personalCargo.getDatoBasico().getCodigoDatoBasico() == 168) {
				a.add(buscarPorCedulaRif(personalCargo.getPersonal().getCedulaRif()));
			}
		}

		return a;
	}

	@Override
	public List<Persona> listarTrabajadores() {
		List<Personal> a = daoPersona.listar(Personal.class);
		List<Persona> b = new ArrayList<Persona>();
		for (Personal personal : a) {
			b.add(buscarPorCedulaRif(personal.getCedulaRif()));
		}

		return b;
	}
	
	
	public boolean  existePersona(String cedula) {
		return daoPersona.existePersona(cedula);
	}
}
