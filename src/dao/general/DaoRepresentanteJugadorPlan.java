package dao.general;

import java.util.List;

import modelo.JugadorPlan;
import modelo.RepresentanteJugadorPlan;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoRepresentanteJugadorPlan extends GenericDao {

	/**
	 * Obtiene lista de representantes por un jugador de plan vacacional
	 * 
	 * @param jugadorPlan
	 *            jugador que pertenece a un plan vacional
	 * @return lista de representantes por jugador
	 * 
	 */
	public List<RepresentanteJugadorPlan> buscarRepresentanteJugador(JugadorPlan jugadorPlan) {
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(RepresentanteJugadorPlan.class)
				.add(Restrictions.eq("jugadorPlan", jugadorPlan))
				.add(Restrictions.eq("estatus", 'A'));
		List<RepresentanteJugadorPlan> lista = c.list();
		return lista;
	}
	
}