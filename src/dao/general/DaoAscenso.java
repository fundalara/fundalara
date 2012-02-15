package dao.general;

import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import modelo.Ascenso;
import modelo.Roster;
import dao.generico.GenericDao;

/**
 * Clase de acceso y manejo para el ascenso por edad y especial 
 * de los jugadores 
 * 
 * @author Maria F
 * @author Romel V
 * @version 0.2 13/01/2012
 */
public class DaoAscenso extends GenericDao {

	/**
	 * Busca y Cambia de estatus el registro anterior del ascenso
	 * 
	 * @param Objeto
	 *            roster
	 */
	public void actualizar(Roster r) {
		Session session = this.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Ascenso.class)
				.add(Restrictions.eq("roster", r))
				.add(Restrictions.eq("estatus", 'A'));
		Ascenso ascenso = (Ascenso) c.uniqueResult();
		if (ascenso != null) {
			ascenso.setEstatus('E');
			session.saveOrUpdate(ascenso);			
		}
		tx.commit();
	}	
	
}
