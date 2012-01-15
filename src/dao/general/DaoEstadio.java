package dao.general;

import java.util.List;

/** 
 * DAO para la clase Estadio
 * @author Alix Villanueva
 * @version 1.0
 *  
 */

import modelo.Divisa;
import modelo.Estadio;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


public class DaoEstadio extends dao.generico.GenericDao{

	/**
	 * Permite listar los Estadios de acuerdo a su estatus
	 * 
	 * @param estatus Estatus del Estadio
	 * @return List<Estadios> Lista de Estadios
	 */
	
	public List<Estadio> listarActivos(){
		/* 
		 * Buscamos en la tabla estadio todo los estadios con estatus 'A' (Activo)
		 * y se listan.
		 */
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Estadio.class);
		c.add(Restrictions.eq("estatus",'A'));
		return c.list();
	}
	
	public List<Estadio> filtar(String cad){
			
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			Criteria c = session.createCriteria(Estadio.class);
			c.add(Restrictions.like("nombre", cad));
			return c.list();
			
		}

}
