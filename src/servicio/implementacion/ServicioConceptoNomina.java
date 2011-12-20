package servicio.implementacion;

import java.util.List;
import servicio.interfaz.IServicioConceptoNomina;
import dao.general.DaoConceptoNomina;
import modelo.ConceptoNomina;
import modelo.Divisa;

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
	public List<ConceptoNomina> listar() {
		return daoConceptoNomina.listar(ConceptoNomina.class);
	}

	@Override
	public List<ConceptoNomina> listarActivos() {
		return daoConceptoNomina.listarActivos(ConceptoNomina.class);
	}

	@Override
	public ConceptoNomina buscarPorCodigo(ConceptoNomina d) {
		// TODO Auto-generated method stub
		return null;
	}

}
