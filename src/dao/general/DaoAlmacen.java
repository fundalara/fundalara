package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.Almacen;
import modelo.Instalacion;

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
	
	public Instalacion buscarInstalacion(Almacen a){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Instalacion instalacion = a.getInstalacion(); 
		return instalacion;
	}

}
