package dao.general;

import java.util.List;

import modelo.ComisionFamiliar;
import modelo.DatoBasico;
import modelo.Instalacion;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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

	public List<Instalacion> buscar(DatoBasico tipo) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(Instalacion.class);
		c.add(Restrictions.eq("datoBasico",tipo));
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}
	
}
