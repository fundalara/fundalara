package dao.generico;

import java.util.List;

import modelo.Divisa;

import org.hibernate.Criteria;
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

/**
 * Clase para manejar las operaciones basicas mediante session factory
 * @author Eduardo Ochoa
 * @version 1.0 
 * @email elos3000@gmail.com
 */
public class GenericDao {
	
	//static Session session;
    public void guardar(Object o) {		
    	Session session = SessionManager.getSession();
		//Session session = getSession();
		Transaction tx =  session.beginTransaction();
		session.saveOrUpdate(o);
		tx.commit();		
	}

	public void actualizar(Object o) {
		//Session session = getSession();
		Session session = SessionManager.getSession();
		Transaction tx =  session.beginTransaction();
		session.saveOrUpdate(o);

		tx.commit();
	}
	
	public void eliminar(Object o) {
		//Session session = getSession();
		Session session = SessionManager.getSession();
		Transaction tx =  session.beginTransaction();
		session.saveOrUpdate(o);

	    tx.commit();
	}
	
	public void eliminarFisico(Object o) {
		//Session session = getSession();
		Session session = SessionManager.getSession();
		Transaction tx =  session.beginTransaction();
		session.delete(o);
	    tx.commit();
	}
	
	public List listar(Class o) {
		//Session session = getSession();
		Session session = SessionManager.getSession();
		Transaction tx =  session.beginTransaction();
		List lista = session.createCriteria(o).list();
		return lista;
	}
	
//	public Session getSession(){
//	if (session == null){
//			return session = SessionManager.getSession();
//	}else{
//		  if(!session.isOpen())
//		      session = SessionManager.getSession();		    
//	      return session;	
//		}
//	}
	
	public Session getSession(){
		return SessionManager.getSession();
	}
	
	public List listarActivos(Class d ){
		//Permite buscar todas las divisas con estatus = 'A'
		//Session session = getSession();
		Session session = SessionManager.getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(d);
		List lista = c.add(Restrictions.eq("estatus",'A')).list();
		return lista;
	}
	
	public int generarCodigo(Class o) {
		//Session session = getSession();
		Session session = SessionManager.getSession();
		Transaction tx =  session.beginTransaction();
		return session.createCriteria(o).list().size()+1;
	}
	
	public Object buscarUnCampo(Class o, String c, Object v) {
		//ession session = getSession();
		Session session = SessionManager.getSession();
		Transaction tx =  session.beginTransaction();
		Criteria cri = session.createCriteria(o);
		cri.add(Restrictions.eq(c, v));
		Object resultado = cri.list().get(0);
	    return resultado;
	}
	
	public Object buscarDosCampos(Class o, String c1, Object v1, String c2, Object v2) {
		//Session session = getSession();
		Session session = SessionManager.getSession();
		Transaction tx =  session.beginTransaction();
		Criteria cri = session.createCriteria(o);
		cri.add(Restrictions.eq(c1, v1));
		cri.add(Restrictions.eq(c2, v2));
		Object resultado = cri.list().get(0);
	    return resultado;
	}
	public List listarUnCampo(Class o,String c, Object v) {
		//Session session = getSession();
		Session session = SessionManager.getSession();
		Transaction tx =  session.beginTransaction();
		Criteria cri = session.createCriteria(o);
		cri.add(Restrictions.eq(c, v));
		List lista = cri.list();
		return lista;
	}
	
	public List listarDosCampos(Class o,String c1, Object v1, String c2, Object v2) {
		Session session = SessionManager.getSession();
		Transaction tx =  session.beginTransaction();
		Criteria cri = session.createCriteria(o);
		cri.add(Restrictions.eq(c1, v1));
		cri.add(Restrictions.eq(c2, v2));
		List lista = cri.list();
		return lista;
	}
}

