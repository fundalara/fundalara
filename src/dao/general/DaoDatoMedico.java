package dao.general;

import modelo.DatoMedico;
import modelo.Jugador;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

/**
 * Clase de acceso y manejo de los datos relacionados a los datos medicos del
 * jugador
 * 
 * @author Robert A
 * @author German L
 * @version 0.2 02/01/2012
 * 
 */
public class DaoDatoMedico extends GenericDao {
	public static String SECUENCIA ="dato_medico_codigo_dato_medico_seq_1";

	/**
	 * Busca el ultimo valor de la clave primaria de la tabla DatoMedico
	 * 
	 * @return int ultimo id
	 */
	public int obtenerUltimoId() {

		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Query query = session
				.createSQLQuery("SELECT last_value FROM "+SECUENCIA);
		int id = Integer.valueOf(query.uniqueResult().toString());
		tx.commit();

		return id;
	}
	
	public DatoMedico buscarDatoMedico(Jugador jugador){
		Session sesion = getSession();
		org.hibernate.Transaction tx = sesion.beginTransaction();
		Criteria c = sesion.createCriteria(DatoMedico.class)
				.addOrder( Order.asc("codigoDatoMedico") )
				.add(Restrictions.eq("estatus",'A'))
				.add(Restrictions.eq("jugador",jugador))
				.setMaxResults(1);
		DatoMedico datoMedico = (DatoMedico) c.uniqueResult();
		tx.commit();
		return datoMedico;
	}

}