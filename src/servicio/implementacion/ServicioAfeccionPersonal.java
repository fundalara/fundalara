package servicio.implementacion;

import java.util.List;
import servicio.interfaz.IServicioAfeccionPersonal;
import modelo.AfeccionPersonal;
import modelo.Personal;
import dao.general.DaoAfeccionPersonal;

public class ServicioAfeccionPersonal implements IServicioAfeccionPersonal {

	DaoAfeccionPersonal daoAfeccionPersonal;
	
	@Override
	public void eliminar(AfeccionPersonal c) {
		daoAfeccionPersonal.eliminar(c);
	}

	@Override
	public void agregar(AfeccionPersonal c) {
		daoAfeccionPersonal.guardar(c);

	}

	@Override
	public void actualizar(AfeccionPersonal c) {
		daoAfeccionPersonal.actualizar(c);

	}

	public DaoAfeccionPersonal getDaoAfeccionPersonal() {
		return daoAfeccionPersonal;
	}

	public void setDaoAfeccionPersonal(DaoAfeccionPersonal daoAfeccionPersonal) {
		this.daoAfeccionPersonal = daoAfeccionPersonal;
	}

	@Override
	public List<AfeccionPersonal> listar() {
		return daoAfeccionPersonal.listar(AfeccionPersonal.class);
	}

	@Override
	public List<AfeccionPersonal> listarActivos() {
		return daoAfeccionPersonal.listarActivos(AfeccionPersonal.class);
	}

	@Override
	public AfeccionPersonal buscarPorCodigo (AfeccionPersonal d) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
