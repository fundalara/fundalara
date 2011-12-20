package servicio.implementacion;

import java.util.List;

import dao.general.DaoPersonalContrato;

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
	public List listar() {
		// TODO Auto-generated method stub
		return daoPersonalContrato.listar(new PersonalContrato());
	}

}
