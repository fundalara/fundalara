package dao.general;

import org.hibernate.Session;


/**
 * Data access interface for domain model
 * @author MyEclipse - Hibernate Tools
 */
public interface IBaseHibernateDAO {
	public Session getSession();

}