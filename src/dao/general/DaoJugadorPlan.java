package dao.general;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import modelo.JugadorPlan;

import dao.generico.GenericDao;

/**
 * Clase de acceso y manejo de los datos relacionados a los jugadores de los planes vacacionales
 * 
 * @author Robert A
 * @author German L
 * @version 0.1.9 10/02/2012
 * 
 */
public class DaoJugadorPlan extends GenericDao {

	/**
	 * Busca los jugadores activos de los planes vacacionales segun lso filtros suministrados
	 * @param cedula filtro de la cedula
	 * @param nombre filtro del nombre
	 * @param apellido filtro del apellido
	 * @return lista de jugadores segun los filtros
	 */
	public List<JugadorPlan> buscarJugadores(String cedula, String nombre, String apellido) {
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = null;
		List<JugadorPlan>   jugadores = new ArrayList<JugadorPlan>();
		c = session.createCriteria(JugadorPlan.class)
				.add(Restrictions.eq("estatus", 'A'))
				.add(Restrictions.like("cedulaRif", cedula + "%"))
				.add(Restrictions.like("nombre", nombre + "%"))
				.add(Restrictions.like("apellido", apellido + "%"));
		jugadores = c.list();
		return jugadores;
	}
	
	public void  guardar(JugadorPlan jugador){
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = null;
		
		c = session.createCriteria(JugadorPlan.class)
				.add(Restrictions.eq("estatus", 'A'))
				.add(Restrictions.eq("cedulaRif", jugador.getCedulaRif()));
					c.setProjection(Projections.rowCount());
		int cantidad = (Integer) c.list().get(0);
	
		if (cantidad==0){
			session.save(jugador);
		}else{
			session.merge(jugador);
		}
	} 
	
}