package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.DatoBasico;
import modelo.Equipo;
import modelo.PlanEntrenamiento;
import modelo.Sesion;
import dao.generico.GenericDao;

public class DaoSesion extends GenericDao {

	public List<Sesion> buscarPorEquipo(Equipo e) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(Sesion.class);
		c.add(Restrictions.eq("equipo", e));
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}
	
	public List<Sesion> buscarPorEquipoDia(Equipo equipo, DatoBasico dia) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(Sesion.class);
		c.add(Restrictions.eq("equipo", equipo));
		c.add(Restrictions.eq("datoBasico", dia));
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}

	public List<Sesion> buscarPorPlanEntrenamiento(PlanEntrenamiento pe) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(Sesion.class);
		c.add(Restrictions.eq("planEntrenamiento", pe));
		c.add(Restrictions.not(Restrictions.eq("estatus", 'E')));
		return c.list();
	}
}
