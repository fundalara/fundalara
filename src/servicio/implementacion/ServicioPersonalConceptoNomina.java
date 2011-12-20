package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonalConceptoNomina;

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
	public List listar() {
		// TODO Auto-generated method stub
		return daoPersonalConceptoNomina.listar(new PersonalConceptoNomina());
	}

}
