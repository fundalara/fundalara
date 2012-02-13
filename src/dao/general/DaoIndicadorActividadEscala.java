package dao.general;
import java.util.List;

import modelo.ActividadEntrenamiento;
import modelo.DatoBasico;
import modelo.IndicadorActividadEscala;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoIndicadorActividadEscala extends GenericDao {
	
	public IndicadorActividadEscala buscarPorCodigo(Integer td) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(IndicadorActividadEscala.class);
		c.add(Restrictions.eq("codigoIndicadorActividadEscala", td));
		c.add(Restrictions.eq("estatus", 'A'));
		return (IndicadorActividadEscala) c.list().get(0);
	}

	public List<IndicadorActividadEscala> buscarPorObjeto(Integer codigo,String campo) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(IndicadorActividadEscala.class);
		c.add(Restrictions.eq(campo, codigo));
		c.add(Restrictions.eq("estatus", 'A'));
		return c.list();
	}
	
	public List<IndicadorActividadEscala> buscarPorActividad(ActividadEntrenamiento ae) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(IndicadorActividadEscala.class);
		c.add(Restrictions.eq("actividadEntrenamiento", ae));
		c.add(Restrictions.eq("estatus", 'A'));
		return c.list();
	}
	
	public List<IndicadorActividadEscala> buscarIndicador(ActividadEntrenamiento actividad) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(IndicadorActividadEscala.class);
		c.add(Restrictions.eq("actividadEntrenamiento", actividad ));
		c.add(Restrictions.eq("estatus", 'A'));
		return c.list();
	}
}
