package dao.general;

import java.util.List;

import modelo.ClasificacionCompetencia;
import modelo.CondicionCompetencia;
import modelo.DatoBasico;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

/** 
 * @author Merielen Gaspar
 * @author Diana Santiago
 * @version 2.0 01/02/2012
 * 
 */

public class DaoCondicionCompetencia extends GenericDao {

	/**
	 * Clase DAO CondicionCompetencia para acceso/manejo de las Condiciones de Competencias.
	 * Proporciona metodos para ser implementados en un ServicioCondicionCompetencia.
	 *  
	 */

	public List<CondicionCompetencia> listarCondicionSeleccionada(ClasificacionCompetencia cc) {

		/**
		 * Permite listar las condiciones seleccionadas para una clasificacion de una competencia
		 * especifica, de acuerdo a su estatus.
		 * 
		 * @param cc  Variable que contiene las clasificaion de una competencia
		 * @return List<CondicionCompetencia> Lista de Condiciones seleccionadas de una clasificacion para una competencia especifica.
		 * 
		 */
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(CondicionCompetencia.class);
		c.add(Restrictions.eq("clasificacionCompetencia", cc));
		c.add(Restrictions.eq("estatus", 'A'));

		return c.list();
	}
}




