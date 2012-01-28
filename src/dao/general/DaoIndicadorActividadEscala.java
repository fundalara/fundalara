package dao.general;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.ActividadEntrenamiento;
import modelo.IndicadorActividadEscala;

import dao.generico.GenericDao;

public class DaoIndicadorActividadEscala extends GenericDao {
	public List<IndicadorActividadEscala> buscarPorActividad(ActividadEntrenamiento ae) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(IndicadorActividadEscala.class);
		c.add(Restrictions.eq("actividadEntrenamiento", ae));
		c.add(Restrictions.eq("estatus", 'A'));
		return c.list();
	}

	public IndicadorActividadEscala buscarPorCodigo(Integer td) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(IndicadorActividadEscala.class);
		c.add(Restrictions.eq("codigoIndicadorActividadEscala", td));
		return (IndicadorActividadEscala) c.list().get(0);
	}
}
