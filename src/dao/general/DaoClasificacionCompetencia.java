package dao.general;

import java.util.List;

import modelo.ClasificacionCompetencia;
import modelo.DatoBasico;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoClasificacionCompetencia extends GenericDao {
	
	public List<ClasificacionCompetencia> listarActivos(){

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(ClasificacionCompetencia.class);
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}	
	
	public List<ClasificacionCompetencia> listarClasificacion(DatoBasico cl){

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(ClasificacionCompetencia.class);
		c.add(Restrictions.eq("estatus",'A'));
		c.add(Restrictions.eq("datoBasico", cl));
		return c.list();
	}	

	

}
