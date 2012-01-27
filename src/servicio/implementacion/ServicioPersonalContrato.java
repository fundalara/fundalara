package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonalContrato;

import modelo.PersonalConceptoNomina;
import modelo.PersonalContrato;
import servicio.interfaz.IServicioPersonalContrato;

public class ServicioPersonalContrato implements IServicioPersonalContrato {
	
	DaoPersonalContrato daoPersonalContrato;
	public DaoPersonalContrato getDaoPersonalContrato() {
		return daoPersonalContrato;
	}

	public void setDaoPersonalContrato(DaoPersonalContrato daoPersonalContrato) {
		this.daoPersonalContrato = daoPersonalContrato;
	}

	@Override
	public void eliminar(PersonalContrato c) {
		daoPersonalContrato.eliminar(c);

	}

	@Override
	public void agregar(PersonalContrato c) {
		daoPersonalContrato.guardar(c);
	}

	@Override
	public void actualizar(PersonalContrato c) {
		daoPersonalContrato.actualizar(c);

	}

	@Override
	public List<PersonalContrato> listar() {
		return daoPersonalContrato.listar(PersonalContrato.class);
	}

	@Override
	public List<PersonalContrato> listarActivos() {
		return daoPersonalContrato.listarActivos(PersonalContrato.class);
	}

	@Override
	public PersonalContrato buscarPorCodigo (PersonalContrato d) {
		// TODO Auto-generated method stub
		return null;
	}

}
