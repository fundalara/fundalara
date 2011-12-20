package servicio.implementacion;

import java.util.List;

import dao.general.DaoActividadesEjecutadas;

import modelo.ActividadesEjecutadas;
import servicio.interfaz.IServicioActividadesEjecutadas;

public class ServicioActividadesEjecutadas implements
		IServicioActividadesEjecutadas {

	DaoActividadesEjecutadas daoActividadesEjecutadas;

	public DaoActividadesEjecutadas getDaoActividadesEjecutadas() {
		return daoActividadesEjecutadas;
	}

	public void setDaoActividadesEjecutadas(
			DaoActividadesEjecutadas daoActividadesEjecutadas) {
		this.daoActividadesEjecutadas = daoActividadesEjecutadas;
	}

	@Override
	public void guardar(ActividadesEjecutadas ae) {
		daoActividadesEjecutadas.guardar(ae);
	}

	@Override
	public void actualizar(ActividadesEjecutadas ae) {
		daoActividadesEjecutadas.actualizar(ae);
	}

	@Override
	public void eliminar(ActividadesEjecutadas ae) {
		daoActividadesEjecutadas.eliminar(ae);
	}

	@Override
	public List<ActividadesEjecutadas> listar() {
		return daoActividadesEjecutadas.listar(ActividadesEjecutadas.class);
	}

}
