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
 * DAO para la clase CondicionCompetencia 
 * 
 * @author Merielen Gaspar
 * @version 2.0
 *  
 */

public class DaoCondicionCompetencia extends GenericDao {
	
	/**
	 * Permite listar las condiciones de una clasificacion de una competencia especifica,
	 * de acuerdo a su estatus
	 * 
	 * @param cc Variable que contiene las condiciones de la competencia
	 * @return List<CondicionCompetencia> Lista de Condiciones
	 */
	
	
}
