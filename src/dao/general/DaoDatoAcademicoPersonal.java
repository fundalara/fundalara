package dao.general;
import java.util.List;

import modelo.DatoAcademicoPersonal;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoDatoAcademicoPersonal extends GenericDao {
	public List<DatoAcademicoPersonal> listarPorCedula(String d) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(DatoAcademicoPersonal.class);
		c.add(Restrictions.eq("personal.cedulaRif", d));
		c.add(Restrictions.eq("estatus", 'A'));
		return (List<DatoAcademicoPersonal>) c.list();
	}
}
