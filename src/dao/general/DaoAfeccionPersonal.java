package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import modelo.AfeccionPersonal;
import dao.generico.GenericDao;

public class DaoAfeccionPersonal extends GenericDao {
	
	public List<AfeccionPersonal> listarPorCedula(String d) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(AfeccionPersonal.class);
		c.add(Restrictions.eq("personal.cedulaRif", d));
		c.add(Restrictions.eq("estatus", 'A'));
		return (List<AfeccionPersonal>) c.list();
	}
}
