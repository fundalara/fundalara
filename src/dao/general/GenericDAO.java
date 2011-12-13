package dao.general;


import java.util.List;



import org.hibernate.Session;

import org.hibernate.Transaction;


import dao.prueba.SessionManager;



public class GenericDAO {



	public void guardar(Object c) {

		

		
		Session session = SessionManager.getSession();
		Transaction tx =  session.beginTransaction();
		session.saveOrUpdate(c);
		tx.commit();

		
	}

	public void actualizar(Object c) {
		Session session = SessionManager.getSession();
		Transaction tx =  session.beginTransaction();
		session.saveOrUpdate(c);
		tx.commit();

	}
	
	public void eliminar(Object c) {
		//getSession().delete(c);
		//getSession().flush();
		//getSession().close();

	}
	
	public List listar(Class c) {
		Session session = SessionManager.getSession();
		Transaction tx =  session.beginTransaction();
		List lista = session.createCriteria(c).list();
		//HibernateSessionFactory.closeSession();
		return lista;
	}
}