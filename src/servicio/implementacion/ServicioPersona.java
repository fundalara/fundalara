package servicio.implementacion;

import java.util.ArrayList;
import java.util.List;

import dao.general.DaoPersona;

import modelo.Nomina;
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
	public List<Persona> listar() {
		return daoPersona.listar(Persona.class);
	}

	@Override
	public List<Persona> listarActivos() {
		return daoPersona.listarActivos(Persona.class);
	}

	@Override
	public Persona buscarPorCodigo (Persona d) {
		// TODO Auto-generated method stub
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
	public List<Persona> listarTrabajadores() {
		// TODO Auto-generated method stub
		List<Persona> a = new ArrayList<Persona>();
		List<Persona> b = new ArrayList<Persona>();
		b = daoPersona.listarActivos(Persona.class);
		for (Persona persona : b) {
			if (persona.getDatoBasicoByCodigoTipoPersona().getCodigoDatoBasico() == 169 || persona.getDatoBasicoByCodigoTipoPersona().getCodigoDatoBasico() ==168){
				a.add(persona);
			}
		}
		
		
		return a;
	}
}
