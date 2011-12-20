package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonalTipoNomina;

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
	public List listar() {
		// TODO Auto-generated method stub
		return daoPersonalTipoNomina.listar(new PersonalTipoNomina());
	}

}
