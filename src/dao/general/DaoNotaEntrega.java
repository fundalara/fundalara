package dao.general;

import java.util.List;

import modelo.NotaEntrega;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoNotaEntrega extends GenericDao {
	
	public int devolverUltimo() {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Query c = session
				.createQuery("SELECT max(codigoNotaEntrega) FROM NotaEntrega");
		return (Integer) c.uniqueResult();
	}

	public NotaEntrega buscarPorCodigo(Integer cod) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(NotaEntrega.class);
		c.add(Restrictions.eq("codigoNotaEntrega", cod));
		List<NotaEntrega> lista = c.list();

		return lista.get(0);
	}

}
