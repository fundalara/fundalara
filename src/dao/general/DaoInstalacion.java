package dao.general;

import dao.generico.GenericDao;

public class DaoInstalacion extends GenericDao {
	
	// METODO PARA BUSCAR (Solo para busqueda de Campos Claves)
	public Object buscar(Class clase, String id) {
		// CREAMOS UN OBJETO
		Object obj = new Object();
		obj = getSession().get(clase, id);
		getSession().flush();
		return obj;
	}
	
	public int contarCodigos(String tabla) {
	int cantidad = 0;
	cantidad = ((Long) getSession().createQuery(
			"SELECT COUNT(*) FROM " + tabla).uniqueResult()).intValue();
	return cantidad;
}

}
