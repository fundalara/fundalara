package dao.logistica;

import java.util.List;

import modelo.Almacen;

import dao.general.GenericDAO;

public class NuevoAlmacenDAO extends GenericDAO {

	public List<Almacen> listarAlmacen() {		
		List<Almacen> l = getSession().createCriteria(Almacen.class).list();
		getSession().flush();
		return l;
	}
	
}
