package dao.general;

import java.util.List;

import modelo.DatoBasico;
import modelo.PersonaNatural;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoPersonaNatural extends GenericDao {

	public PersonaNatural buscarPersonaNatural(String d ) {
		System.out.println("llego");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(PersonaNatural.class);
		c.add(Restrictions.eq("cedulaRif", d));
		PersonaNatural objectPersona = (PersonaNatural) c.list().get(0);
		return objectPersona;
	}
}
