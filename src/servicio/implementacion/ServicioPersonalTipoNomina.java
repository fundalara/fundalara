package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonalTipoNomina;

import modelo.PersonalContrato;
import modelo.PersonalTipoNomina;
import servicio.interfaz.IServicioPersonalTipoNomina;

public class ServicioPersonalTipoNomina implements IServicioPersonalTipoNomina {
	
	DaoPersonalTipoNomina daoPersonalTipoNomina;
	public DaoPersonalTipoNomina getDaoPersonalTipoNomina() {
		return daoPersonalTipoNomina;
	}

	public void setDaoPersonalTipoNomina(DaoPersonalTipoNomina daoPersonalTipoNomina) {
		this.daoPersonalTipoNomina = daoPersonalTipoNomina;
	}

	@Override
	public void eliminar(PersonalTipoNomina c) {
		daoPersonalTipoNomina.eliminar(c);

	}

	@Override
	public void agregar(PersonalTipoNomina c) {
		daoPersonalTipoNomina.guardar(c);

	}

	@Override
	public void actualizar(PersonalTipoNomina c) {
		daoPersonalTipoNomina.actualizar(c);
	}

	@Override
	public List<PersonalTipoNomina> listar() {
		return daoPersonalTipoNomina.listar(PersonalTipoNomina.class);
	}

	@Override
	public List<PersonalTipoNomina> listarActivos() {
		return daoPersonalTipoNomina.listarActivos(PersonalTipoNomina.class);
	}

	@Override
	public PersonalTipoNomina buscarPorCodigo (PersonalTipoNomina d) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PersonalTipoNomina buscarPorCedulaRif(String s) {
		// TODO Auto-generated method stub
		return daoPersonalTipoNomina.buscarPorCedulaRif(s);
	}
}
