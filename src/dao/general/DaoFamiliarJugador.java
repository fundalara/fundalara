package dao.general;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import modelo.DatoBasico;
import modelo.Familiar;
import modelo.Jugador;
import modelo.FamiliarJugador;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

/**
 * Clase de acceso y manejo de los datos relacionados a los familiares de los
 * jugadores
 * 
 * @author Robert A
 * @author German L
 * @version 0.2 10/02/2012
 * 
 */
public class DaoFamiliarJugador extends GenericDao {

	public List<FamiliarJugador> buscarFamiliarJugador(Jugador jugador) {
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(FamiliarJugador.class)
				.add(Restrictions.eq("jugador", jugador))
				.add(Restrictions.eq("estatus", 'A'));
		List<FamiliarJugador> lista = c.list();
		return lista;
	}

	public FamiliarJugador buscar(Familiar familiar) {
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		FamiliarJugador elemento = new FamiliarJugador();
		Criteria c = session.createCriteria(FamiliarJugador.class)
				.add(Restrictions.eq("familiar", familiar))
				.add(Restrictions.eq("estatus", 'A'));
		List<FamiliarJugador> lista = c.list();

		if (lista.size() >= 1) {
			elemento = lista.get(0);
		}
		return elemento;
	}

	/**
	 * Guarda/actualiza las asociaciones de un jugador con una lista de
	 * familiares
	 * 
	 * @param familiaresJugadores
	 *            lista de familiares
	 * @param jugador
	 *            jugador
	 */
	public void guardar(List<FamiliarJugador> familiaresJugadores,
			Jugador jugador) {
		Session sesion = getSession();
		Transaction tx = sesion.beginTransaction();
		int p = 0;
		List<FamiliarJugador> datos = new ArrayList<FamiliarJugador>();
		datos.addAll(familiaresJugadores);

		Criteria c = sesion.createCriteria(FamiliarJugador.class)
				.createCriteria("jugador")
				.add(Restrictions.eq("cedulaRif", jugador.getCedulaRif()));

		List<FamiliarJugador> datosAlmacenados = (List<FamiliarJugador>) c
				.list();

		for (FamiliarJugador datoAlmacenado : datosAlmacenados) {
			p = buscarDatoPorCodigo(datos, datoAlmacenado);
			if (p == -1) {
				datoAlmacenado.setEstatus('E');
				datoAlmacenado.setRepresentanteActual(false);
			} else {
				datoAlmacenado.setDatoBasico(datos.get(p).getDatoBasico());
				datoAlmacenado.setRepresentanteActual(datos.get(p)
						.isRepresentanteActual());

				if (datoAlmacenado.getEstatus() == 'E') {
					datoAlmacenado.setEstatus('A');
				}
				datos.remove(p);
			}
			sesion.update(datoAlmacenado);
		}
		/*
		 * Procesamos los valores que hayan quedado en la lista , estos
		 * restantes representan los nuevos datos que ha agregado el usuario
		 */
		for (FamiliarJugador datoNuevo : datos) {
			datoNuevo.setFechaIngreso(new Date());
			datoNuevo.setEstatus('A');
			sesion.save(datoNuevo);
		}
		tx.commit();
	}

	private int buscarDatoPorCodigo(List<FamiliarJugador> datos,
			FamiliarJugador datoBuscar) {
		int posicion = -1;
		int i = 0;
		for (FamiliarJugador datoJugador : datos) {
			if (datoJugador.getFamiliar().getCedulaRif()
					.equals(datoBuscar.getFamiliar().getCedulaRif())
					&& datoJugador.getJugador().getCedulaRif()
							.equals(datoBuscar.getJugador().getCedulaRif())) {
				posicion = i;
				break;
			}
			i++;
		}
		return posicion;
	}

	/**
	 * Busca el parentesco de un familiar con un jugador dado en caso de que
	 * exista
	 * 
	 * @param familiar
	 * @param cedulaJugador
	 * @return parentesco o null si no existe relacion entre el familiar y el
	 *         jugador
	 */
	public DatoBasico buscarParentesco(Familiar familiar, String cedulaJugador) {
		Session sesion = getSession();
		Transaction tx = sesion.beginTransaction();

		Criteria c = sesion.createCriteria(FamiliarJugador.class)
				.add(Restrictions.eq("familiar", familiar))
				.createCriteria("jugador")
				.add(Restrictions.eq("cedulaRif", cedulaJugador));

		FamiliarJugador dato = (FamiliarJugador) c.uniqueResult();

		tx.commit();
		return dato == null ? null : dato.getDatoBasico();
	}

	public Familiar buscarRepresentanteActual(String cedulaJugador) {
		Session sesion = getSession();
		Transaction tx = sesion.beginTransaction();
		Criteria c1 = sesion.createCriteria(FamiliarJugador.class)
				.add(Restrictions.eq("representanteActual", true))
				.add(Restrictions.eq("jugador.cedulaRif", cedulaJugador));
		FamiliarJugador familiarJugador = (FamiliarJugador) c1.uniqueResult();
		tx.commit();
		return familiarJugador == null ? null : familiarJugador.getFamiliar();
	}

	public void actualizarRepresentante(Familiar familiar,
			String cedulaJugador, boolean sw) {
		Session sesion = getSession();
		Transaction tx = sesion.beginTransaction();
		Criteria c1 = sesion.createCriteria(FamiliarJugador.class)
				.add(Restrictions.eq("familiar", familiar))
				.add(Restrictions.eq("jugador.cedulaRif", cedulaJugador));
		FamiliarJugador familiarJugador = (FamiliarJugador) c1.uniqueResult();
		familiarJugador.setRepresentanteActual(sw);
		sesion.update(familiarJugador);
		tx.commit();
	}

	public int generarSecuencia(Familiar familiar){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		int cantidad=0;
		Criteria criteria = session.createCriteria(FamiliarJugador.class);
				criteria.setProjection(Projections.rowCount())
				.add(Restrictions.eq("familiar", familiar))
				.add(Restrictions.like("jugador.cedulaRif", "R-%-%"))
				.add(Restrictions.eq("representanteActual", true));
		cantidad = (Integer)criteria.list().get(0)+1; 
		tx.commit();
		return cantidad;
	}
	
	public boolean esRepresentanteActual(String cedulaJugador, Familiar familiar) {
		boolean sw=false;
		Session sesion = getSession();
		Transaction tx = sesion.beginTransaction();
		Criteria c1 = sesion.createCriteria(FamiliarJugador.class)
				.add(Restrictions.eq("familiar", familiar))
				.add(Restrictions.eq("representanteActual", true))
				.add(Restrictions.eq("jugador.cedulaRif", cedulaJugador));
		FamiliarJugador familiarJugador = (FamiliarJugador) c1.uniqueResult();
		tx.commit();
		if (familiarJugador!=null){
			sw=true;
		}
		return sw;
	}
	
	public List<FamiliarJugador> buscarPorRepresentante(Familiar d){	
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(FamiliarJugador.class);
		c.add(Restrictions.eq("familiar",d)).list();
		if (c.list().isEmpty()){
			return null;
		}else
			return c.list();
	}
	
}