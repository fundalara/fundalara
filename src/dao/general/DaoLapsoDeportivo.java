package dao.general;

import java.util.List;

import modelo.DatoBasico;
import modelo.LapsoDeportivo;
import modelo.Material;
import dao.generico.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class DaoLapsoDeportivo extends GenericDao {

	public LapsoDeportivo buscarPorTipoLapso(DatoBasico db) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria where = getSession().createCriteria(LapsoDeportivo.class);
		where.add(Restrictions.eq("datoBasico", db));
		where.add(Restrictions.eq("estatus", 'A'));
		return (LapsoDeportivo) where.uniqueResult();
	}

	public List<LapsoDeportivo> listarActivos() {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(LapsoDeportivo.class);
		c.add(Restrictions.eq("estatus", 'A'));
		return c.list();
	}

}
