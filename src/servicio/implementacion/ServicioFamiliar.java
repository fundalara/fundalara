package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioFamiliar;

import dao.general.DaoFamiliar;
import modelo.Familiar;

public class ServicioFamiliar implements IServicioFamiliar {

	DaoFamiliar daoFamiliar;
	
	public DaoFamiliar getDaoFamiliar() {
		return daoFamiliar;
	}

	public void setDaoFamiliar(DaoFamiliar daoFamiliar) {
		this.daoFamiliar = daoFamiliar;
	}

	@Override
	public void eliminar(Familiar c) {
		daoFamiliar.eliminar(c);

	}

	@Override
	public void agregar(Familiar c) {
		daoFamiliar.guardar(c);

	}

	@Override
	public void actualizar(Familiar c) {
		daoFamiliar.actualizar(c);

	}

	@Override
	public List<Familiar> listar() {
		return daoFamiliar.listar( Familiar.class);
	}

}
