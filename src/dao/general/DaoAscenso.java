package dao.general;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import modelo.Ascenso;
import modelo.Roster;
import dao.generico.GenericDao;

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
