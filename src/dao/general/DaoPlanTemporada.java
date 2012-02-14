package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.ActividadEntrenamiento;
import modelo.Categoria;
import modelo.LapsoDeportivo;
import modelo.PlanTemporada;
import dao.generico.GenericDao;

public class DaoPlanTemporada extends GenericDao{

	public PlanTemporada buscarPorCategoriaLapsoDeportivo(Categoria ct, LapsoDeportivo ld) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria where =  getSession().createCriteria(PlanTemporada.class);
		where.add(Restrictions.eq("categoria", ct));
		where.add(Restrictions.eq("lapsoDeportivo", ld));
		where.add(Restrictions.not(Restrictions.eq("estatus", 'E')));
//		buscarDosCamposActivos(PlanTemporada.class, "categoria", ct,"lapsoDeportivo", ld)
		return (PlanTemporada) where.uniqueResult();
	}
	
	public List<PlanTemporada> buscarPorLapsoDeportivo(LapsoDeportivo ld){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria where =  getSession().createCriteria(PlanTemporada.class);
		where.add(Restrictions.eq("lapsoDeportivo", ld));
		where.add(Restrictions.eq("estatus", "A"));
		return where.list();
	}
}
