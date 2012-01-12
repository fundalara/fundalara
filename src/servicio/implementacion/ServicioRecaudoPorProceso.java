package servicio.implementacion;

import java.util.List;

import comun.TipoDatoBasico;

import servicio.interfaz.IServicioRecaudoPorProceso;

import dao.general.DaoRecaudoPorProceso;

import modelo.DatoBasico;
import modelo.RecaudoPorProceso;

/**
 * Clase para brindar los servicios para manejar los datos relacionados con los
 * recaudos solicitados en los diversos procesos a los jugadores
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 29/12/2011
 * 
 */
public class ServicioRecaudoPorProceso implements IServicioRecaudoPorProceso {

	DaoRecaudoPorProceso daoRecaudoPorProceso;
	
	public DaoRecaudoPorProceso getDaoRecaudoPorProceso() {
		return daoRecaudoPorProceso;
	}

	public void setDaoRecaudoPorProceso(DaoRecaudoPorProceso daoRecaudoPorProceso) {
		this.daoRecaudoPorProceso = daoRecaudoPorProceso;
	}

	@Override
	public void eliminar(RecaudoPorProceso c) {
		daoRecaudoPorProceso.eliminar(c);

	}

	@Override
	public void agregar(RecaudoPorProceso c) {
		daoRecaudoPorProceso.guardar(c);

	}

	@Override
	public void actualizar(RecaudoPorProceso c) {
		daoRecaudoPorProceso.actualizar(c);

	}

	@Override
	public List<RecaudoPorProceso> listar() {
		return daoRecaudoPorProceso.listar( RecaudoPorProceso.class);
	}

	@Override
	public List<RecaudoPorProceso> buscarPorProceso(DatoBasico proceso,
			TipoDatoBasico tipoDocumento, String nombre) {
		return daoRecaudoPorProceso.buscarPorProceso(proceso, tipoDocumento,
				nombre);
	}

}
