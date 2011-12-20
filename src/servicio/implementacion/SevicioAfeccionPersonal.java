package servicio.implementacion;

import java.util.List;
import servicio.interfaz.IServicioAfeccionPersonal;
import modelo.AfeccionPersonal;
import dao.general.DaoAfeccionPersonal;

public class SevicioAfeccionPersonal implements IServicioAfeccionPersonal {

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
	public List listar() {
		// TODO Auto-generated method stub
		return daoAfeccionPersonal.listar(new AfeccionPersonal());
	}

	

}
