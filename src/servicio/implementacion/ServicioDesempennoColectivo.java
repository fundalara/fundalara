package servicio.implementacion;

import java.util.List;

import dao.general.DaoDesempennoColectivo;

import modelo.DesempennoColectivo;
import servicio.interfaz.IServicioDesempennoColectivo;

public class ServicioDesempennoColectivo implements
		IServicioDesempennoColectivo {
	
	DaoDesempennoColectivo daoDesempennoColectivo;

	public DaoDesempennoColectivo getDaoDesempennoColectivo() {
		return daoDesempennoColectivo;
	}

	public void setDaoDesempennoColectivo(
			DaoDesempennoColectivo daoDesempennoColectivo) {
		this.daoDesempennoColectivo = daoDesempennoColectivo;
	}

	@Override
	public void eliminar(DesempennoColectivo d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(DesempennoColectivo d) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DesempennoColectivo> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DesempennoColectivo> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
