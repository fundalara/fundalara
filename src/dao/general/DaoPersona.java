package dao.general;

import modelo.Persona;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoPersona extends GenericDao {
	public Persona buscarByCedulaRif(String s){	
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(Persona.class);
		c.add(Restrictions.eq("cedulaRif",s)).add(Restrictions.eq("estatus", 'A')).list();
		return (Persona)c.list().get(0);
	}
}
