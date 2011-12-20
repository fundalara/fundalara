package dao.general;

import java.util.List;

import modelo.Almacen;

import dao.generico.GenericDao;

public class DaoAlmacen extends GenericDao {

	public List<Almacen> listarAlmacen() {		
		List<Almacen> l = getSession().createCriteria(Almacen.class).list();
		getSession().flush();
		return l;
	}
	
	public int contarCodigos(String tabla) {
	int cantidad = 0;
	cantidad = ((Long) getSession().createQuery(
			"SELECT COUNT(*) FROM " + tabla).uniqueResult()).intValue();
	return cantidad;
}
	
}
