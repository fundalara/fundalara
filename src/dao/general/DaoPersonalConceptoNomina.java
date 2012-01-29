package dao.general;
import java.util.List;

import modelo.ConceptoNomina;
import modelo.Personal;
import modelo.PersonalConceptoNomina;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoPersonalConceptoNomina extends GenericDao {
	public List<PersonalConceptoNomina> listarConceptosByPersonal(Personal d) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonalConceptoNomina.class);
		c.add(Restrictions.eq("personal", d)).list();
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}

	public PersonalConceptoNomina buscarConceptoByPersonal(
			ConceptoNomina concp, Personal p) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonalConceptoNomina.class);
		c.add(Restrictions.eq("conceptoNomina", concp));
		c.add(Restrictions.eq("personal", p));
		if (c.list().size() == 0)
			return null;
		else
			return (PersonalConceptoNomina) c.list().get(0);
	}
}
