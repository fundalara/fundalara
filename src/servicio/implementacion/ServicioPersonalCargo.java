package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonalCargo;

import modelo.DatoBasico;
import modelo.Nomina;
import modelo.Personal;
import modelo.PersonalCargo;
import servicio.interfaz.IServicioPersonalCargo;

public class ServicioPersonalCargo implements IServicioPersonalCargo {
	
	DaoPersonalCargo daoPersonalCargo;
	public DaoPersonalCargo getDaoPersonalCargo() {
		return daoPersonalCargo;
	}

	public void setDaoPersonalCargo(DaoPersonalCargo daoPersonalCargo) {
		this.daoPersonalCargo = daoPersonalCargo;
	}

	@Override
	public void eliminar(PersonalCargo c) {
		daoPersonalCargo.eliminar(c);

	}

	@Override
	public void agregar(PersonalCargo c) {
		daoPersonalCargo.guardar(c);
	}

	@Override
	public void actualizar(PersonalCargo c) {
		daoPersonalCargo.actualizar(c);
	}

	@Override
	public List<PersonalCargo> listar() {
		return daoPersonalCargo.listar(PersonalCargo.class);
	}

	@Override
	public List<PersonalCargo> listarActivos() {
		return daoPersonalCargo.listarActivos(PersonalCargo.class);
	}

	@Override
	public PersonalCargo buscarPorCodigo (PersonalCargo d) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<PersonalCargo> buscarListaCargo(DatoBasico db){
		return daoPersonalCargo.listarUnCampo(PersonalCargo.class, "datoBasico", db);
	}

	public List<PersonalCargo> listarPorCargo(DatoBasico cargo) {
		return daoPersonalCargo.listarUnCampo(PersonalCargo.class, "datoBasico", cargo);
	}
	
	public PersonalCargo buscarCargoActual(Personal personal) {
		return daoPersonalCargo.buscarCargoActual(personal);
	}
	
	public PersonalCargo buscarPorCodigo(int codigo) {
		return daoPersonalCargo.buscarPorCodigo(codigo);
	}
	
	public List<PersonalCargo> buscarHistorial(Personal personal) {
		return daoPersonalCargo.buscarHistorial(personal);
	}


	
}
