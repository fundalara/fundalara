package dao.general;

import java.util.List;

import modelo.Actividad;
import modelo.MaterialActividad;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoMaterialActividad extends GenericDao {
	
	public List listarActividad(Actividad a) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(MaterialActividad.class);
		List lista = c.add(Restrictions.eq("actividad", a)).list();
		return lista;
	}

}
