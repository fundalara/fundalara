package dao.general;

import java.util.List;

public class GenericDAO extends BaseHibernateDAO {

	public void guardar(Object c) {
		getSession().saveOrUpdate(c);
		getSession().flush();
		getSession().close();
	}

	public void actualizar(Object c) {
		getSession().saveOrUpdate(c);
		getSession().flush();
		getSession().close();
	}

	public void eliminar(Object c) {
		getSession().delete(c);
		getSession().flush();
		getSession().close();
	}

	public List listar(Object o) {
		List l =getSession().createCriteria(o.getClass()).list();
		getSession().flush();
		getSession().close();
		return l;
	}

	// METODO PARA BUSCAR (Solo para busqueda de Campos Claves)
	public Object buscar(Class clase, String id) {
		// CREAMOS UN OBJETO
		Object obj = new Object();
		obj = getSession().get(clase, id);
		getSession().flush();
		getSession().close();
		return obj;
	}

	public int contarCodigos(Object o) {
		int cantidad = getSession().createCriteria(o.getClass()).list().size();
		getSession().close();
		return cantidad;
	}

}