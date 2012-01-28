package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.ActividadEntrenamiento;
import modelo.Categoria;
import modelo.DatoBasico;
import dao.generico.GenericDao;

public class DaoActividadEntrenamiento extends GenericDao {
	public ActividadEntrenamiento buscarClaveForegn(Categoria c, DatoBasico f,int idActividad){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria where =  session.createCriteria(ActividadEntrenamiento.class);
		where.add(Restrictions.eq("categoria", c));
		where.add(Restrictions.eq("datoBasico", f));
		where.add(Restrictions.eq("codActividadEntrenamiento", idActividad));
		where.add(Restrictions.eq("estatus", "A"));
		return (ActividadEntrenamiento)where.list().get(0);
	}
	
	public List<ActividadEntrenamiento> buscarTodo(Categoria c, DatoBasico f){		
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria where =  session.createCriteria(ActividadEntrenamiento.class);
		where.add(Restrictions.eq("categoria", c));
		where.add(Restrictions.eq("datoBasico", f));		
		where.add(Restrictions.eq("estatus", "A"));
		return where.list();
	}

	public List<ActividadEntrenamiento> buscarPorCategoria(Categoria c) {
		// TODO Auto-generated method stub
		Criteria where =  getSession().createCriteria(ActividadEntrenamiento.class);
		where.add(Restrictions.eq("categoria", c));
		return where.list();
	}
	
	public ActividadEntrenamiento buscarporCodigo(Integer i){		
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria where =  getSession().createCriteria(ActividadEntrenamiento.class);
		where.add(Restrictions.eq("codigoActividadEntrenamiento", i));
		return (ActividadEntrenamiento)where.list().get(0);
	}
}
