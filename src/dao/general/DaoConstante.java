package dao.general;

import java.util.List;

import modelo.Categoria;
import modelo.Constante;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

/** 
 * DAO para la clase Constante 
 * 
 * @author Niurka G�mez
 * @version 1.0
 *  
 */

public class DaoConstante extends dao.generico.GenericDao {

	/**
	 * Permite listar las categorias de acuerdo a su estatus
	 * 
	 * @param estatus Estatus de la competencia
	 * @return List<Categoria> Lista de Categoria
	 */
	
	public List<Constante> listarActivos(){
		/* 
		 * Buscamos en la tabla Categoria todas aquellas con el estatus = 'A'
		 */
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Constante.class);
		c.add(Restrictions.eq("estatus",'A'));
		return c.list();
	}
	public List<Constante> listarConstantesActivos(){
		/* 
		 * Buscamos en la tabla Constante todas aquellas con el estatus = 'A'
		 */
				Session session = getSession();
				Transaction tx = session.beginTransaction();
				Criteria c = session.createCriteria(Constante.class);
				c.add(Restrictions.eq("estatus",'A'));
				return c.list();
	}

}