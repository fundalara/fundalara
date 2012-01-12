package dao.general;


import java.util.List;

import modelo.TipoCompetencia;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoTipoCompetencia extends GenericDao {
	
	// Busca todos los tipos de competencias con (estatus = 1)
	public List<TipoCompetencia> listarActivos(){

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(TipoCompetencia.class);
		c.add(Restrictions.eq("estatus",'A'));
		return c.list();
	}	

}