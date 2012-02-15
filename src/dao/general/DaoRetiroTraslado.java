package dao.general;

import dao.generico.GenericDao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import modelo.Jugador;
import modelo.RetiroTraslado;

/**
 * Clase de acceso y manejo de los datos relacionados a los retiros y traslados ocurridos en la divisa
 * 
 * @author Robert A
 * @author German L
 * @author Edgar L
 * @version 0.1.9 10/01/2012
 * 
 */
public class DaoRetiroTraslado extends GenericDao {

	/**
	 * Realiza el conteo del numero de filas en una tabla de datos
	 * @param r objeto retirotraslado a ser contado
	 * @param operacion valor del tipo de operacion seleccionado
	 */
	
	public int contarfilas (RetiroTraslado r, int operacion){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		int cantidad=0;
		Criteria criteria = session.createCriteria(RetiroTraslado.class);
				criteria.setProjection(Projections.rowCount()).
				createCriteria("datoBasicoByCodigoTipoOperacion")
				.add(Restrictions.eq("codigoDatoBasico", operacion));
		cantidad = (Integer)criteria.list().get(0); 
		tx.commit();
		return cantidad;
	}
	
	/**
	 * Actualiza el estatus de un retiro a 'jugador reingresado'
	 * @param jugador jugador que reingresa a la divisa
	 */
	public void reingresarJugador(Jugador jugador){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria criteria = session.createCriteria(RetiroTraslado.class)
		.add(Restrictions.eq("jugador", jugador))
		.add(Restrictions.eq("estatus", 'A'));
		
		RetiroTraslado retiro= (RetiroTraslado) criteria.uniqueResult();
		if (retiro!=null){
			retiro.setEstatus('R');
			session.update(retiro);
		}
		tx.commit();
	}
	
}