package dao.general;

import java.util.List;

import modelo.Ingreso;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoIngreso extends GenericDao {

	public List<Ingreso> ListarRecibo() {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(Ingreso.class);
		c.add(Restrictions.sqlRestriction("numero_documento <> ''"));

		return (List<Ingreso>) c.list();
	}
}
