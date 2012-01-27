package dao.general;

import java.util.List;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.Estadio;
import modelo.Juego;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;


import comun.EstadoCompetencia;

import dao.generico.GenericDao;
import dao.generico.SessionManager;

/** 
 * DAO para la clase Competencia 
 * 
 * @author Nohely Pernalete
 * @author Gabrielena Ponce
 * @version 1.0
 *  
 */
public class DaoCompetencia extends GenericDao {
    
	
	/**
	 * Permite listar las competencias de acuerdo a su estatus
	 * 
	 * @param estatus Estatus de la competencia
	 * @return List<Competencia> Lista de Competencias
	 */
	
	public List<Competencia> listarPorEstatus(int estatus){
	  	
		/* 
		 * Buscamos en la tabla DatoBasico el objeto correspondiente para el estatus
		 * de competencia especificado. Luego con este objeto buscamos en la tabla
		 * Competencia.
		 * 
		 */
		//Session session = getSession();                         
		Session session = SessionManager.getSession();
		Transaction tx = session.beginTransaction();   
		
		Criteria c = session.createCriteria(DatoBasico.class);	
		
		c.add(Restrictions.eq("codigoDatoBasico",estatus)); 
		DatoBasico db = (DatoBasico) c.list().get(0);                               
		
		

		c = session.createCriteria(Competencia.class);
		c.add(Restrictions.eq("datoBasicoByCodigoEstadoCompetencia",db));
		return c.list();
			
	}
	
	public List<Competencia> listarRegistradasAperturadas() {

		Session session = getSession();
		Transaction tx = session.beginTransaction();

		Criteria c = session.createCriteria(DatoBasico.class);

		c.add(Restrictions.eq("codigoDatoBasico", EstadoCompetencia.REGISTRADA));
		DatoBasico dbr = (DatoBasico) c.list().get(0);

		c = session.createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("codigoDatoBasico", EstadoCompetencia.APERTURADA));
		DatoBasico dba = (DatoBasico) c.list().get(0);

		Criteria q = session.createCriteria(Competencia.class);
		Criterion cr1 = Restrictions.eq("datoBasicoByCodigoEstadoCompetencia",
				dbr);
		Criterion cr2 = Restrictions.eq("datoBasicoByCodigoEstadoCompetencia",
				dba);
		q.add(Restrictions.or(cr1, cr2));

		return q.list();

	}

	
	

}
