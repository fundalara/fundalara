package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.Equipo;
import modelo.RosterCompetencia;
import dao.generico.GenericDao;

public class DaoRosterCompetencia extends GenericDao {

	public List<RosterCompetencia> listarPorEquipo(Equipo e) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(RosterCompetencia.class);
		c.add(Restrictions.eq("equipo", e))
				.add(Restrictions.eq("estatus", 'A'));
		List<RosterCompetencia> lista = c.list();
		return lista;
	}
}
