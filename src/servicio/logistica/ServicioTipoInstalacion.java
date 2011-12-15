package servicio.logistica;

import java.util.List;

import dao.general.GenericDAO;

import modelo.TipoInstalacion;

public class ServicioTipoInstalacion {

	GenericDAO dTInstalacion;
	
	

	public GenericDAO getdTInstalacion() {
		return dTInstalacion;
	}

	public void setdTInstalacion(GenericDAO dTInstalacion) {
		this.dTInstalacion = dTInstalacion;
	}

	
	public void eliminar(TipoInstalacion c) {
		dTInstalacion.eliminar(c);
	}


	public void guardar(TipoInstalacion c) {
		dTInstalacion.guardar(c);

	}

	
	public void actualizar(TipoInstalacion c) {
		dTInstalacion.actualizar(c);
	}

	
	public TipoInstalacion buscar(String c) {
	 return (TipoInstalacion) dTInstalacion.buscar(TipoInstalacion.class,c);

	}

	
	public List<TipoInstalacion> listar() {
		return dTInstalacion.listar(new TipoInstalacion());
	}

}
