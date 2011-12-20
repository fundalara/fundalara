package servicio.implementacion;

import java.util.List;

import dao.general.DaoNomina;

import modelo.Nomina;
import servicio.interfaz.IServicioNomina;

public class ServicioNomina implements IServicioNomina {
	
	DaoNomina daoNomina;
	public DaoNomina getDaoNomina() {
		return daoNomina;
	}

	public void setDaoNomina(DaoNomina daoNomina) {
		this.daoNomina = daoNomina;
	}

	@Override
	public void eliminar(Nomina c) {
		daoNomina.eliminar(c);

	}

	@Override
	public void agregar(Nomina c) {
		daoNomina.guardar(c);
	}

	@Override
	public void actualizar(Nomina c) {
		daoNomina.actualizar(c);

	}

	@Override
	public List listar() {
		// TODO Auto-generated method stub
		return daoNomina.listar(new Nomina());
	}

}
