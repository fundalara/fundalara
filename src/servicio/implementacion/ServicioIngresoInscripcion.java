package servicio.implementacion;

import java.util.List;

import dao.general.DaoIngresoInscripcion;

import modelo.IngresoInscripcion;
import servicio.interfaz.IServicioIngresoInscripcion;

public class ServicioIngresoInscripcion implements IServicioIngresoInscripcion {
	DaoIngresoInscripcion daoIngresoInscripcion = new DaoIngresoInscripcion();
	public DaoIngresoInscripcion getDaoIngresoInscripcion() {
		return daoIngresoInscripcion;
	}
	public void setDaoIngresoInscripcion(DaoIngresoInscripcion daoIngresoInscripcion) {
		this.daoIngresoInscripcion = daoIngresoInscripcion;
	}
	public List<IngresoInscripcion> listarPorTipoInscripcion(int i){
		return daoIngresoInscripcion.listarPorTipoInscripcion(i);
	}
}
