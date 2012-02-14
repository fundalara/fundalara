package servicio.implementacion;

import java.util.List;

import modelo.DatoBasico;
import modelo.DocumentoConducta;
import modelo.IngresoInscripcion;
import modelo.PersonalEquipo;
import servicio.interfaz.IServicioIngresoInscripcion;
import dao.general.DaoIngresoInscripcion;

public class ServicioIngresoInscripcion implements IServicioIngresoInscripcion {
	DaoIngresoInscripcion daoIngresoInscripcion;
	public DaoIngresoInscripcion getDaoIngresoInscripcion() {
		return daoIngresoInscripcion;
	}

	public void setDaoIngresoInscripcion(DaoIngresoInscripcion daoIngresoInscripcion) {
		this.daoIngresoInscripcion = daoIngresoInscripcion;
	}

	@Override
	public void eliminar(DocumentoConducta c) {
		daoIngresoInscripcion.eliminar(c);
	}

	@Override
	public void agregar(DocumentoConducta c) {
		daoIngresoInscripcion.guardar(c);

	}

	@Override
	public void actualizar(DocumentoConducta c) {
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
