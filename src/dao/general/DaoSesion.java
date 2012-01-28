package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.Equipo;
import modelo.Sesion;
import dao.generico.GenericDao;

public class DaoSesion extends GenericDao {
	public List<Sesion> buscarPorEquipo(Equipo e) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(Sesion.class);
		c.add(Restrictions.eq("equipo", e));
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}
}
