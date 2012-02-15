package dao.general;

import java.util.List;

import modelo.DatoConducta;
import modelo.Jugador;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoDatoConducta extends GenericDao {
	
	/**
	 * Obtener lista de Datos de Conducta por un jugador
	 * @param jugador 
	 * 			dato de jugador a buscar
	 */
	public List<DatoConducta> buscarPorJugador(Jugador jugador) {
		Session sesion = getSession();
		Transaction tx = sesion.beginTransaction();
		Criteria c = sesion.createCriteria(DatoConducta.class)	
		.add(Restrictions.eq("jugador", jugador));
		return c.list();
	}

}