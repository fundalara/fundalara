package servicio.implementacion;

import java.util.List;

import dao.general.DaoActividadCalendario;
import modelo.ActividadCalendario;
import modelo.DatoBasico;
import servicio.interfaz.IServicioActividadCalendario;

public class ServicioActividadCalendario implements
		IServicioActividadCalendario {

	DaoActividadCalendario daoActividadCalendario;
	
	@Override
	public void eliminar(ActividadCalendario ac) {
		// TODO Auto-generated method stub
		daoActividadCalendario.eliminar(ac);
	}

	@Override
	public void agregar(ActividadCalendario ac) {
		// TODO Auto-generated method stub
		daoActividadCalendario.guardar(ac);
	}

	@Override
	public void actualizar(ActividadCalendario ac) {
		// TODO Auto-generated method stub
		daoActividadCalendario.actualizar(ac);
	}

	public DaoActividadCalendario getDaoActividadCalendario() {
		return daoActividadCalendario;
	}

	public void setDaoActividadCalendario(
			DaoActividadCalendario daoActividadCalendario) {
		this.daoActividadCalendario = daoActividadCalendario;
	}

	public List<ActividadCalendario> listarPorTipoActividad(DatoBasico tipoActividad){
		return daoActividadCalendario.listarPorTipoActividad(tipoActividad);
	}
	
	public int generarCodigo(){
		return daoActividadCalendario.generarCodigo(ActividadCalendario.class);
	}

}
