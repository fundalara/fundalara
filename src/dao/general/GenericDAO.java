package dao.general;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import dao.prueba.HibernateUtil;



public class GenericDAO {



	public void guardar(Object c) {

		

		
		Session session = HibernateSessionFactory.currentSession();
		Transaction tx =  session.beginTransaction();
		session.saveOrUpdate(c);
		tx.commit();
		//session.close();
		
	}

	public void actualizar(Object c) {
		Session session = HibernateSessionFactory.currentSession();
		Transaction tx =  session.beginTransaction();
		session.saveOrUpdate(c);
		tx.commit();
		//session.close();
	}
	
	public void eliminar(Object c) {
		//getSession().delete(c);
		//getSession().flush();
		//getSession().close();

	}
	
	public List listar(Object o) {
		Session session = HibernateSessionFactory.currentSession();
		Transaction tx =  session.beginTransaction();
		List lista = session.createCriteria(o.getClass()).list();
		tx.commit();
		//HibernateSessionFactory.closeSession();
		return lista;
	}
}