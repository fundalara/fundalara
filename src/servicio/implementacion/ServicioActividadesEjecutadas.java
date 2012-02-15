package servicio.implementacion;

import java.util.List;

import dao.general.DaoActividadesEjecutadas;

import modelo.ActividadEjecutada;
import modelo.SesionEjecutada;
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
	public void guardar(ActividadEjecutada ae) {
		daoActividadesEjecutadas.guardar(ae);
	}

	@Override
	public void actualizar(ActividadEjecutada ae) {
		daoActividadesEjecutadas.actualizar(ae);
	}

	@Override
	public void eliminar(ActividadEjecutada ae) {
		daoActividadesEjecutadas.eliminar(ae);
	}

	@Override
	public List<ActividadEjecutada> listar() {
		return daoActividadesEjecutadas.listar(ActividadEjecutada.class);
	}

	public List<ActividadEjecutada> buscarPorSesionEjecutada(SesionEjecutada sesionEjecutada) {
		// TODO Auto-generated method stub
		return daoActividadesEjecutadas.buscarPorSesionEjecutada(sesionEjecutada);
	}

}
