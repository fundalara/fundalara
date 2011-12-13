package dao.prueba;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.YesNoType;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
public class GenericDao {
	

	public void guardar(Object o) {		
		Session session = SessionManager.getSession();
		Transaction tx =  session.beginTransaction();
		session.saveOrUpdate(o);
		tx.commit();		
	}

	public void actualizar(Object o) {
		Session session = SessionManager.getSession();
		Transaction tx =  session.beginTransaction();
		session.saveOrUpdate(o);
		tx.commit();
	}
	
	public void eliminar(Object o) {
		Session session = SessionManager.getSession();
		Transaction tx =  session.beginTransaction();
		session.saveOrUpdate(o);
	    tx.commit();
	}
	
	public List listar(Class o) {
		Session session = SessionManager.getSession();
		Transaction tx =  session.beginTransaction();
		List lista = session.createCriteria(o).list();
		return lista;
	}
	
	public Session getSession(){
		return SessionManager.getSession();
	}
}
