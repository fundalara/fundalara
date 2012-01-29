package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonalConceptoNomina;

import modelo.ConceptoNomina;
import modelo.Personal;
import modelo.PersonalCargo;
import modelo.PersonalConceptoNomina;
import servicio.interfaz.IServicioPersonalConceptoNomina;

public class ServicioPersonalConceptoNomina implements
		IServicioPersonalConceptoNomina {
	
	DaoPersonalConceptoNomina daoPersonalConceptoNomina;
	public DaoPersonalConceptoNomina getDaoPersonalConceptoNomina() {
		return daoPersonalConceptoNomina;
	}

	public void setDaoPersonalConceptoNomina(
			DaoPersonalConceptoNomina daoPersonalConceptoNomina) {
		this.daoPersonalConceptoNomina = daoPersonalConceptoNomina;
	}

	@Override
	public void eliminar(PersonalConceptoNomina c) {
		daoPersonalConceptoNomina.eliminar(c);

	}

	@Override
	public void agregar(PersonalConceptoNomina c) {
		daoPersonalConceptoNomina.guardar(c);

	}

	@Override
	public void actualizar(PersonalConceptoNomina c) {
		daoPersonalConceptoNomina.actualizar(c);
	}

	@Override
	public List<PersonalConceptoNomina> listar() {
		return daoPersonalConceptoNomina.listar(PersonalConceptoNomina.class);
	}

	@Override
	public List<PersonalConceptoNomina> listarActivos() {
		return daoPersonalConceptoNomina.listarActivos(PersonalConceptoNomina.class);
	}

	@Override
	public PersonalConceptoNomina buscarPorCodigo (PersonalConceptoNomina d) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<PersonalConceptoNomina> listarConceptosByPersonal(Personal d) {
		return daoPersonalConceptoNomina.listarConceptosByPersonal(d);
	}
	
	
	public PersonalConceptoNomina buscarConceptoByPersonal(ConceptoNomina c, Personal p) {
		return daoPersonalConceptoNomina.buscarConceptoByPersonal(c,p);
	}

}
