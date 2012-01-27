package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioRepresentante;

import dao.general.DaoRepresentante;

public class ServicioRepresentante implements IServicioRepresentante {

	DaoRepresentante daoRepresentante;
	
	public DaoRepresentante getDaoRepresentante() {
		return daoRepresentante;
	}

	public void setDaoRepresentante(DaoRepresentante daoRepresentante) {
		this.daoRepresentante = daoRepresentante;
	}

	@Override
	public void agregar(modelo.Representante c) {
		daoRepresentante.guardar(c);
	}

	@Override
	public List<modelo.Representante> listar() {
		return daoRepresentante.listar( ServicioRepresentante.class);
	}

}
