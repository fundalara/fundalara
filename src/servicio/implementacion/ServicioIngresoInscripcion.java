package servicio.implementacion;

import java.util.List;

import dao.general.DaoIngresoInscripcion;

import modelo.DatoBasico;
import modelo.IngresoInscripcion;
import servicio.interfaz.IServicioIngresoInscripcion;

public class ServicioIngresoInscripcion implements IServicioIngresoInscripcion {
	DaoIngresoInscripcion daoIngresoInscripcion;
	public DaoIngresoInscripcion getDaoIngresoInscripcion() {
		return daoIngresoInscripcion;
	}

	public void setDaoIngresoInscripcion(DaoIngresoInscripcion daoIngresoInscripcion) {
		this.daoIngresoInscripcion = daoIngresoInscripcion;
	}

	@Override
	public void eliminar(IngresoInscripcion c) {
		daoIngresoInscripcion.eliminar(c);
	}

	@Override
	public void agregar(IngresoInscripcion c) {
		daoIngresoInscripcion.guardar(c);

	}

	@Override
	public void actualizar(IngresoInscripcion c) {
		daoIngresoInscripcion.actualizar(c);

	}

	@Override
	public List<IngresoInscripcion> listar() {
		return  daoIngresoInscripcion.listar(IngresoInscripcion.class);
	}
	
	public List<IngresoInscripcion> listarIngresoInscripcion(DatoBasico tipoInscripcion){
		return daoIngresoInscripcion.listarTipoInscrpcion(tipoInscripcion);
	}
}

