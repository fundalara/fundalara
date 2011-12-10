package dao.logistica;

import java.util.List;
import modelo.UnidadMedida;
import dao.general.GenericDAO;

public class UnidadMedidaDAO extends GenericDAO {

	public List<UnidadMedida> listarUnidadesMedida() {
		// TODO Auto-generated method stub		
		return getSession().createCriteria(UnidadMedida.class).list();
	}
}
