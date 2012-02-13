package dao.general;
import java.util.List;

import modelo.Personal;
import modelo.PersonalCargo;
import dao.generico.GenericDao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


public class DaoPersonalCargo extends GenericDao {
	
	public PersonalCargo buscarCargoActual(Personal personal) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonalCargo.class);
		c.add(Restrictions.eq("personal", personal)).list();
		c.add(Restrictions.eq("estatus", "A"));
		if (c.list().size() == 0)
			return null;
		else
			return (PersonalCargo) c.list().get(0);
	}
	
	public PersonalCargo buscarPorCodigo(int codigo) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonalCargo.class);
		c.add(Restrictions.eq("codigoPersonalCargo", codigo)).list();
		if (c.list().size() == 0)
			return null;
		else
			return (PersonalCargo) c.list().get(0);
	}
	
	public List<PersonalCargo> buscarHistorial(Personal personal) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonalCargo.class);
		c.add(Restrictions.eq("personal", personal));
		return c.list();
	}

	public List<PersonalCargo> buscarPorCargo(DatoBasico db) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonalCargo.class);
		c.add(Restrictions.eq("datoBasico", db));
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}
}
