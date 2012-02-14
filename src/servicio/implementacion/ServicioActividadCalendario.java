package servicio.implementacion;

import java.util.Date;
import java.util.List;

import dao.general.DaoActividadCalendario;
import modelo.ActividadCalendario;
import modelo.ActividadEntrenamiento;
import modelo.DatoBasico;
import modelo.Sesion;
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

	public List<ActividadCalendario> listar() {
		List<ActividadCalendario> a = daoActividadCalendario.listar(ActividadCalendario.class);
		return a;
	}
	
	public DaoActividadCalendario getDaoActividadCalendario() {
		return daoActividadCalendario;
	}

	public void setDaoActividadCalendario(
			DaoActividadCalendario daoActividadCalendario) {
		this.daoActividadCalendario = daoActividadCalendario;
	}
	
	public ActividadCalendario buscarPorCodigo(Integer i) {
		// TODO Auto-generated method stub
		return daoActividadCalendario.buscarporCodigo(i);
	}
	
	public int generarCodigo(){
		return daoActividadCalendario.generarCodigo(ActividadCalendario.class);
	}
	
	public List<ActividadCalendario> listarPorTipoActividad(DatoBasico tipoActividad){
		return daoActividadCalendario.listarPorTipoActividad(tipoActividad);
	}

	@Override
	public ActividadCalendario buscarSesionFecha(Date fecha, Sesion sesion) {
		return daoActividadCalendario.buscarSesionFecha(fecha, sesion);
	}
}