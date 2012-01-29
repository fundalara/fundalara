package dao.general;

import modelo.PersonalContrato;
import modelo.PersonalTipoNomina;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoPersonalTipoNomina extends GenericDao {
	public PersonalTipoNomina buscarPorCedulaRif(String s) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonalTipoNomina.class);
		c.add(Restrictions.eq("personal.cedulaRif", s))
				.add(Restrictions.eq("estatus", 'A'));
		if (c.list().isEmpty())
			return null;
		else
			return (PersonalTipoNomina) c.list().get(0);
	}
}
