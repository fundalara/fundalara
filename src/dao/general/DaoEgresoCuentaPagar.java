package dao.general;
import java.util.List;

import dao.generico.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.CuentaPagar;
import modelo.EgresoCuentaPagar;


public class DaoEgresoCuentaPagar extends GenericDao {
	
	public List<EgresoCuentaPagar> listarPorCuentaPagar(CuentaPagar td) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(EgresoCuentaPagar.class);
		c.add(Restrictions.eq("cuentaPagar", td));
		return c.list();
	}
}
