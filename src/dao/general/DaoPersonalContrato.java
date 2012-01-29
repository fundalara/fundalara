package dao.general;

import modelo.PersonalContrato;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import dao.generico.GenericDao;

public class DaoPersonalContrato extends GenericDao {

	public PersonalContrato buscarPorCedulaRif(String s) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonalContrato.class);
		c.add(Restrictions.eq("personal.cedulaRif", s))
				.add(Restrictions.eq("estatus", 'A'));
		if (c.list().isEmpty())
			return null;
		else
			return (PersonalContrato) c.list().get(0);
	}
}
