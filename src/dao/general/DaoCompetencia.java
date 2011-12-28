package dao.general;

import java.util.List;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.Estadio;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import comun.EstadoCompetencia;

import dao.generico.GenericDao;

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
		Session session = getSession();                         
		Transaction tx = session.beginTransaction();   
		
		Criteria c = session.createCriteria(DatoBasico.class);	
		c.add(Restrictions.eq("codigoDatoBasico",estatus)); 
		DatoBasico db = (DatoBasico) c.list().get(0);                               
		
		
		System.out.println(db.getCompetenciasForCodigoEstado().size());
		c = session.createCriteria(Competencia.class);
		c.add(Restrictions.eq("datoBasicoByCodigoEstadoCompetencia",db));
		return c.list();
			
	}
}
