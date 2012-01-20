package dao.general;
import modelo.DatoBasico;
import modelo.PersonalForaneo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import modelo.DatoBasico;
import modelo.PersonalForaneo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoPersonalForaneo extends GenericDao {
	
	public List<PersonalForaneo> listarUmpires() {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(DatoBasico.class);
		DatoBasico db = (DatoBasico) c.add(Restrictions.eq("nombre", "UMPIRE"))
				.list().get(0);
		c = session.createCriteria(PersonalForaneo.class);
		List<PersonalForaneo> pf = c.add(Restrictions.eq("datoBasico", db))
				.list();
		return pf;

	}

	public DatoBasico consultarDatoBasico() {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(DatoBasico.class);
		DatoBasico db = (DatoBasico) c.add(Restrictions.eq("nombre", "UMPIRE"))
				.list().get(0);

		return db;
	}
	
	
	
	
	
	


}
