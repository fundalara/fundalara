package dao.general;

import java.util.List;

import modelo.LapsoDeportivo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoLapsoDeportivo extends GenericDao {
	public List<LapsoDeportivo> listarActivos(){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(LapsoDeportivo.class);
		c.add(Restrictions.eq("estatus",'A'));
		return c.list();
	}


}
