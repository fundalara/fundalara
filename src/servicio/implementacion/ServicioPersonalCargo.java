package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonalCargo;

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
	public List listar() {
		// TODO Auto-generated method stub
		return daoPersonalCargo.listar(new PersonalCargo());
	}

}
