package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonalEquipo;

import modelo.PersonalEquipo;
import servicio.interfaz.IServicioPersonalEquipo;

public class ServicioPersonalEquipo implements IServicioPersonalEquipo {

	DaoPersonalEquipo daoPersonalEquipo;
	
	public DaoPersonalEquipo getDaoPersonalEquipo() {
		return daoPersonalEquipo;
	}

	public void setDaoPersonalEquipo(DaoPersonalEquipo daoPersonalEquipo) {
		this.daoPersonalEquipo = daoPersonalEquipo;
	}

	@Override
	public void guardar(PersonalEquipo pe) {
		daoPersonalEquipo.guardar(pe);
	}

	@Override
	public void actualizar(PersonalEquipo pe) {
		daoPersonalEquipo.actualizar(pe);
	}

	@Override
	public void eliminar(PersonalEquipo pe) {
		daoPersonalEquipo.eliminar(pe);
	}

	@Override
	public List<PersonalEquipo> listar() {
		return daoPersonalEquipo.listar(PersonalEquipo.class);
	}

}
