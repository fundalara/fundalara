package dao.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.PlanEntrenamiento;
import modelo.PlanTemporada;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoPlanEntrenamiento extends GenericDao {
	
	public List<PlanEntrenamiento> buscarporPlanTemporada(PlanTemporada pt) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria where =  getSession().createCriteria(PlanEntrenamiento.class);
		where.add(Restrictions.eq("planTemporada", pt));
		where.add(Restrictions.not(Restrictions.eq("estatus", 'E')));
		return where.list();
	}
}
