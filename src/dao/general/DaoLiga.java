package dao.general;

import dao.generico.GenericDao;

import java.util.List;

import modelo.Liga;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class DaoLiga extends GenericDao{
	public List<Liga> listarActivos(){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Liga.class);
		c.add(Restrictions.eq("estatus",'A'));
		return c.list();
	}

}
