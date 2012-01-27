package servicio.implementacion;

import java.util.List;

import dao.general.DaoEgresoCuentaPagar;

import modelo.Egreso;
import modelo.EgresoCuentaPagar;
import servicio.interfaz.IServicioEgresoCuentaPagar;

public class ServicioEgresoCuentaPagar implements IServicioEgresoCuentaPagar {
	
	DaoEgresoCuentaPagar daoEgresoCuentaPagar;
	public DaoEgresoCuentaPagar getDaoEgresoCuentaPagar() {
		return daoEgresoCuentaPagar;
	}

	public void setDaoEgresoCuentaPagar(DaoEgresoCuentaPagar daoEgresoCuentaPagar) {
		this.daoEgresoCuentaPagar = daoEgresoCuentaPagar;
	}

	@Override
	public void eliminar(EgresoCuentaPagar c) {
		daoEgresoCuentaPagar.eliminar(c);

	}

	@Override
	public void agregar(EgresoCuentaPagar c) {
		daoEgresoCuentaPagar.guardar(c);

	}

	@Override
	public void actualizar(EgresoCuentaPagar c) {
		daoEgresoCuentaPagar.actualizar(c);

	}

	@Override
	public List<EgresoCuentaPagar> listar() {
		return daoEgresoCuentaPagar.listar(EgresoCuentaPagar.class);
	}

	@Override
	public List<EgresoCuentaPagar> listarActivos() {
		return daoEgresoCuentaPagar.listarActivos(EgresoCuentaPagar.class);
	}

	@Override
	public EgresoCuentaPagar buscarPorCodigo (EgresoCuentaPagar d) {
		// TODO Auto-generated method stub
		return null;
	}

}
