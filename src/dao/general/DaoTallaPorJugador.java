package dao.general;

import java.util.ArrayList;
import java.util.List;

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
	public void guardar(Jugador jugador, DatoBasico tipoIndumentaria,
			DatoBasico... tallas) {
		TallaPorJugador tallaJugador = null;
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();

		for (DatoBasico talla : tallas) {
			if (talla != null) {
				Criteria c = session
						.createCriteria(TallaPorIndumentaria.class)
						.add(Restrictions.eq("datoBasicoByCodigoTalla", talla))
						.add(Restrictions.eq("estatus", 'A'))
						.add(Restrictions.eq("datoBasicoByCodigoTipoUniforme",
								tipoIndumentaria));
				TallaPorIndumentaria indumentaria = (TallaPorIndumentaria) c
						.uniqueResult();
				if (indumentaria != null) {
					tallaJugador = new TallaPorJugador();
					tallaJugador.setId(new TallaPorJugadorId(indumentaria
							.getCodigoTallaIndumentaria(), jugador
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

	/**
	 * Actualiza las tallas de un jugador manteniendo una sola tala, por tipo de
	 * indumentaria
	 * 
	 * @param jugador
	 *            datos del jugador que al que se le actualizan las tallas
	 * @param tipoIndumentaria
	 *            tipo de uso de indumentaria
	 * @param tallas
	 *            arreglo de tallas nuevas
	 */
	public void actualizar(Jugador jugador, DatoBasico tipoIndumentaria,
			DatoBasico... tallas) {
		TallaPorJugador tallaJug = null;
		boolean esCandidataReg = true;
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		for (DatoBasico talla : tallas) {
			if (talla != null) {
				Criteria c = session
						.createCriteria(TallaPorIndumentaria.class)
						.add(Restrictions.eq("datoBasicoByCodigoTalla", talla))
						.add(Restrictions.eq("estatus", 'A'))
						.add(Restrictions.eq("datoBasicoByCodigoTipoUniforme",
								tipoIndumentaria));
				TallaPorIndumentaria indumentariaNueva = (TallaPorIndumentaria) c
						.uniqueResult();
				if (indumentariaNueva != null) {
					Criteria c2 = session.createCriteria(TallaPorJugador.class);
					c2.add(Restrictions.eq("jugador", jugador));
					c2.createCriteria("tallaPorIndumentaria").add(Restrictions.eq("datoBasicoByCodigoTipoUniforme", tipoIndumentaria));
					List<TallaPorJugador> tallasRegistradas = c2.list();

					TallaPorJugador tallaEncontrada = null;
					for (TallaPorJugador tallaPorJugador : tallasRegistradas) {
						if (tallaPorJugador.getTallaPorIndumentaria()
								.getCodigoTallaIndumentaria() == indumentariaNueva
								.getCodigoTallaIndumentaria()) {
							esCandidataReg = false;
							tallaEncontrada = tallaPorJugador;
							break;
						}
					}

					Criteria c3 = session
							.createCriteria(TallaPorIndumentaria.class)
							.add(Restrictions.eq(
									"datoBasicoByCodigoTipoUniforme",
									tipoIndumentaria))
							.add(Restrictions.eq("estatus", 'A'))
							.add(Restrictions.ne("codigoTallaIndumentaria",
									indumentariaNueva
											.getCodigoTallaIndumentaria()))
							.createCriteria("datoBasicoByCodigoTalla")
							.add(Restrictions
									.eq("datoBasico.codigoDatoBasico", talla
											.getDatoBasico()
											.getCodigoDatoBasico()));
					List<TallaPorIndumentaria> tallasRelacionadas = c3.list();

					if (esCandidataReg) {
						/*
						 * verificar que no exista ya un valor que indentifique
						 * a la misma indumentaria En caso de encontrar se
						 * elimina logicamente para mantener un solo registro
						 * por indumentaria
						 */
						tallaJug = buscarTallaActualizar(tallasRegistradas,
								tallasRelacionadas);
						if (tallaJug != null) {
							tallaJug.setEstatus('E');
							session.update(tallaJug);
						}
						// Se guarda la talla
						TallaPorJugador tallaJugador = new TallaPorJugador();
						tallaJugador.setId(new TallaPorJugadorId(
								indumentariaNueva.getCodigoTallaIndumentaria(),
								jugador.getCedulaRif()));
						tallaJugador.setJugador(jugador);
						tallaJugador.setTallaPorIndumentaria(indumentariaNueva);
						tallaJugador.setEstatus('A');
						session.save(tallaJugador);
					} else {
						/*
						 * Sino es candidata a registrar, sera candidata a
						 * actualizar
						 */
						if (tallaEncontrada.getEstatus() == 'E') {
							/*
							 * Se debe eliminar la talla relacioanda almacenada
							 * que exista, y reactivar la talla encontrada
							 */
							tallaJug = buscarTallaActualizar(tallasRegistradas,
									tallasRelacionadas);
							if (tallaJug != null) {
								tallaJug.setEstatus('E');
								session.update(tallaJug);
							}
							tallaEncontrada.setEstatus('A');
							session.update(tallaEncontrada);
						}
					}
					tallaJug = null;
					esCandidataReg = true;
				}
			}
		}

		tx.commit();
	}

	/**
	 * Busca la talla relacionada que se encuentra registrada de manera activa.
	 * 
	 * @param tallasRegistradas
	 *            lista de tallas registradas previamente
	 * @param tallasRelacionadas
	 *            lista de tallas realciondas a la talla a registrar/actualizar
	 * @return talla relacionada (activa) a la talla nueva, en caso de no haber
	 *         null
	 */
	private TallaPorJugador buscarTallaActualizar(
			List<TallaPorJugador> tallasRegistradas,
			List<TallaPorIndumentaria> tallasRelacionadas) {
		TallaPorJugador talla = null;
		cicloExterno: for (TallaPorJugador tallaReg : tallasRegistradas) {
			for (TallaPorIndumentaria tallaRel : tallasRelacionadas) {
				if ((tallaReg.getTallaPorIndumentaria()
						.getCodigoTallaIndumentaria() == tallaRel
						.getCodigoTallaIndumentaria())
						&& (tallaReg.getEstatus() == 'A')) {
					talla = tallaReg;
					break cicloExterno;
				}
			}
		}
		return talla;
	}

	/**
	 * Busca la talla relacionada a un jugador y un tipo de indumentaria
	 * 
	 * @param jugador
	 *            Clase jugador a la cual se quiere obtener sus tallas
	 * @param tipoIndumentaria
	 *            Tipo de indumentaria a la cual se quiere obtener su talla
	 * @return lista con las tallas obtenidas, en caso de no haber null
	 */	
	public List<DatoBasico> buscarTallasPorTipo(Jugador jugador,
			DatoBasico tipoIndumentaria) {
		List<DatoBasico> lista = new ArrayList<DatoBasico>();
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(TallaPorJugador.class);
		c.add(Restrictions.eq("jugador", jugador));
		c.add(Restrictions.eq("estatus", 'A'));
		List<TallaPorJugador> tallasRegistradas = c.list();
		for (TallaPorJugador tallaPorJugador : tallasRegistradas) {
			DatoBasico db = tallaPorJugador.getTallaPorIndumentaria().getDatoBasicoByCodigoTalla();
			DatoBasico aux =new DatoBasico(db.getCodigoDatoBasico(),db.getTipoDato(),db.getNombre(),db.getEstatus());
			db=db.getDatoBasico();
			aux.setDatoBasico(new  DatoBasico(db.getCodigoDatoBasico(),db.getTipoDato(),db.getNombre(),db.getEstatus()));
			lista.add(aux);
		}
		tx.commit();
		return lista;
	}

}