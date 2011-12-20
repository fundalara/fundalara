package servicio.implementacion;

import java.util.List;
import servicio.interfaz.IServicioConceptoNomina;
import dao.general.DaoConceptoNomina;
import modelo.ConceptoNomina;

public class ServicioConceptoNomina implements IServicioConceptoNomina {

	DaoConceptoNomina daoConceptoNomina;
	public DaoConceptoNomina getDaoConceptoNomina() {
		return daoConceptoNomina;
	}

	public void setDaoConceptoNomina(DaoConceptoNomina daoConceptoNomina) {
		this.daoConceptoNomina = daoConceptoNomina;
	}

	@Override
	public void eliminar(ConceptoNomina c) {
		daoConceptoNomina.eliminar(c);

	}

	@Override
	public void agregar(ConceptoNomina c) {
		daoConceptoNomina.guardar(c);

	}

	@Override
	public void actualizar(ConceptoNomina c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
