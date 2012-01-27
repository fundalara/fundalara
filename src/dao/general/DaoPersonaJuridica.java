package dao.general;

import modelo.PersonaJuridica;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoPersonaJuridica extends GenericDao {

	
	public PersonaJuridica buscarPorCedulaRif(String d){	
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonaJuridica.class);
		c.add(Restrictions.eq("cedulaRif",d)).list();
		return (PersonaJuridica)c.list().get(0);
	}
}
