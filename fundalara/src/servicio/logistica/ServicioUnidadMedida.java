package servicio.logistica;

import java.util.List;

import dao.logistica.UnidadMedidaDAO;
import modelo.UnidadMedida;

public class ServicioUnidadMedida implements IServicioUnidadMedida {

	UnidadMedidaDAO unidadMedidaDAO;
	
	@Override
	public void eliminar(UnidadMedida um) {
		// TODO Auto-generated method stub
		unidadMedidaDAO.eliminar(um);
	}

	@Override
	public void agregar(UnidadMedida um) {
		// TODO Auto-generated method stub
		unidadMedidaDAO.guardar(um);
	}

	@Override
	public void actualizar(UnidadMedida um) {
		// TODO Auto-generated method stub
		unidadMedidaDAO.actualizar(um);
	}

	@Override
	public List<UnidadMedida> listarUnidadesMedida() {
		// TODO Auto-generated method stub
		return unidadMedidaDAO.listarUnidadesMedida();
	}

	public UnidadMedidaDAO getUnidadMedidaDAO() {
		return unidadMedidaDAO;
	}

	public void setUnidadMedidaDAO(UnidadMedidaDAO unidadMedidaDAO) {
		this.unidadMedidaDAO = unidadMedidaDAO;
	}		

}
