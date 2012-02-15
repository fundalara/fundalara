package dao.general;

import modelo.JugadorPlan;
import modelo.RosterPlan;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

/**
 * Clase de acceso y manejo de los datos relacionados al roster de los equipos
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 02/01/2012
 * 
 */
public class DaoRosterPlan extends GenericDao {

	/**
	 * Busca el registro del roster al que pertenece un jugador
	 * 
	 * @param datos del jugador
	 * @return Un objeto Roster, en caso de no encontrar retornará  null
	 */
	public RosterPlan buscarRoster(JugadorPlan jugador) {
		Session session = this.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(RosterPlan.class)
				.add(Restrictions.eq("jugadorPlan", jugador))
				.add(Restrictions.eq("estatus", 'A'));
		return (RosterPlan) c.uniqueResult();
	}
	
	/**
	 * Retira un jugador inscrito previamente en un plan vacacional
	 * 
	 * @param jugador
	 * 			Dato de jugador a retirar
	 * */
	public void retirarJugador(JugadorPlan jugador){
		Session session = this.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(RosterPlan.class)
				.add(Restrictions.eq("jugadorPlan", jugador))
				.add(Restrictions.eq("estatus", 'A'));
		RosterPlan r=  (RosterPlan) c.uniqueResult();
		r.setEstatus('E');
		session.update(r);
		tx.commit();
	}
	
	
}
