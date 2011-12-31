package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import modelo.EscalaMedicion;
import dao.generico.GenericDao;

public class DaoEscalaMedicion extends GenericDao {

	public EscalaMedicion buscarCodigo(Integer codigo){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria criteria = getSession().createCriteria(EscalaMedicion.class);
		criteria.add(Restrictions.eq("codigoEscala", codigo));
		return (EscalaMedicion) criteria.list().get(0);
	}
}
