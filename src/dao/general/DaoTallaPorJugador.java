package dao.general;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.DatoBasico;
import modelo.Jugador;
import modelo.TallaPorIndumentaria;
import modelo.TallaPorJugador;
import modelo.TallaPorJugadorId;
import dao.generico.GenericDao;

/**
 * Clase de acceso y manejo de los datos relacionados a las tallas de los
 * jugadores
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 01/01/2012
 * 
 */
public class DaoTallaPorJugador extends GenericDao {

	/**
	 * Guarda las tallas de indumentarias (calzado, camisa, ...) de un jugador
	 * 
	 * @param jugador
	 *            Jugador al que se le asociaran las tallas
	 * @param tallas
	 *            tallas de las indumentarias deportivas
	 */
	public void guardar(Jugador jugador, DatoBasico... tallas) {
		TallaPorJugador tallaJugador = null;
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();

		for (DatoBasico talla : tallas) {
			if (talla != null) {
				Criteria c = session
						.createCriteria(TallaPorIndumentaria.class)
						.add(Restrictions
								.eq("datoBasico", talla))
						.add(Restrictions.eq("estatus", 'A'));
					//	.add(Restrictions.eq("datoBasicoByCodigoIndumentaria",	talla.getDatoBasico()));
				TallaPorIndumentaria indumentaria = (TallaPorIndumentaria) c
						.uniqueResult();
				if (indumentaria != null) {
					tallaJugador = new TallaPorJugador();
					tallaJugador.setId(new TallaPorJugadorId(indumentaria
							.getCodigoTallaIndumentaria(),jugador
							.getCedulaRif()));
					tallaJugador.setJugador(jugador);
					tallaJugador.setTallaPorIndumentaria(indumentaria);
					tallaJugador.setEstatus('A');
					session.save(tallaJugador);
				}
			}
		}
		tx.commit();
	}

}
