package dao.prueba;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GenericDao {
    
	public void guardar(Object c) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		Transaction tx =  session.beginTransaction();
		session.save(c);
		tx.commit();
		session.close();
		
	}

	public void actualizar(Object c) {
		
	}
	
	public void eliminar(Object c) {
		
	}
	
	public List listar(Object o) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		
		Session session = sf.getCurrentSession();
		Transaction tx =  session.beginTransaction();
		List lista = session.createCriteria(o.getClass()).list();
		session.close();
		return lista;
	}
}
