package dao.general;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.springframework.transaction.annotation.Transactional;



public class GenericDAO extends  BaseHibernateDAO{



	public void guardar(Object c) {
		getSession().saveOrUpdate(c);
		getSession().flush();
	}

	public void actualizar(Object c) {
		getSession().saveOrUpdate(c);
		getSession().flush();
	}
	
	public void eliminar(Object c) {
		getSession().delete(c);
		getSession().flush();

	}
	
	public List listar(Object o) {
		return getSession().createCriteria(o.getClass()).list();
	}
}