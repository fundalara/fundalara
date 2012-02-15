package dao.general;

import java.util.List;

import modelo.MotivoSancion;
import modelo.Jugador;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoMotivoSancion extends GenericDao {
	
	/**
	 * Busca las sanciones pertinentes a un jugador
	 * 
	 * @param jugador
	 *            Jugador al cual se desea obtener sus sanciones
	 * @return Lista con los datos de sanciones del jugador, en caso de no
	 *         existir se retorna null
	 */	
	public List<MotivoSancion> buscarPorJugador(Jugador jugador) {
		Session sesion = getSession();
		Transaction tx = sesion.beginTransaction();
		Criteria c = sesion.createCriteria(MotivoSancion.class)
		.createCriteria("datoConducta")
		.add(Restrictions.eq("jugador", jugador));
		return c.list();
	}

}