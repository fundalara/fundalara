package dao.general;


import java.util.List;

import modelo.DatoAcademico;
import modelo.Jugador;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


import dao.generico.GenericDao;

/**
 * Clase de acceso y manejo de los datos relacionados a los datos medicos del
 * jugador
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 02/01/2012
 * 
 */
public class DaoDatoAcademico extends GenericDao {
	public static String SECUENCIA ="dato_academico_codigo_academico_seq_1";

	/**
	 * Busca el ultimo valor de la clave primaria de la tabla DatoAcademico
	 * 
	 * @return int ultimo id
	 */
	public int obtenerUltimoId() {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Query query = session
				.createSQLQuery("SELECT last_value FROM "+SECUENCIA);
		int id = Integer.valueOf(query.uniqueResult().toString());
		tx.commit();

		return id;
	}
	
	/**
	 * Busca los datos academicos pertinentes a un jugador
	 * 
	 * @param jugador
	 *            Jugador al cual se desea obtener sus datos academicos
	 * @return Lista con los datos academicos del jugador, en caso de no
	 *         existir se retorna null
	 */	
	public List<DatoAcademico> buscarPorJugador(Jugador jugador) {
		Session sesion = getSession();
		Transaction tx = sesion.beginTransaction();
		Criteria c = sesion.createCriteria(DatoAcademico.class)	
		.add(Restrictions.eq("jugador", jugador));
		return c.list();
	}
	
	public DatoAcademico buscarDatoAcademico(Jugador jugador){
		Session sesion = getSession();
		org.hibernate.Transaction tx = sesion.beginTransaction();
		Criteria c = sesion.createCriteria(DatoAcademico.class)
				.add(Restrictions.eq("jugador",jugador))
				.add(Restrictions.eq("estatus",'A'))
				.addOrder( Order.desc("codigoAcademico") )
				.setMaxResults(1);
		DatoAcademico datoAcademico = (DatoAcademico) c.uniqueResult();
		tx.commit();
		return datoAcademico;

	}
}
