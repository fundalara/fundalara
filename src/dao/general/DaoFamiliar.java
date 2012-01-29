package dao.general;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.Familiar;
import dao.generico.GenericDao;

public class DaoFamiliar extends GenericDao {
	
	public Familiar buscarPorCedulaFamiliar(String d){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(Familiar.class);
		c.add(Restrictions.eq("personaNatural.cedulaRif",d)).list();
		if (c.list().isEmpty()){
			return null;
		}else{
			return (Familiar) c.list().get(0);
			}
	}


}
