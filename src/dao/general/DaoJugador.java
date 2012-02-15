package dao.general;

import java.util.ArrayList;
import java.util.List;

import modelo.DatoBasico;
import modelo.Familiar;
import modelo.Jugador;
import modelo.LapsoDeportivo;
import modelo.Persona;
import modelo.PersonaNatural;
import modelo.RetiroTraslado;
import modelo.Roster;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import comun.EstatusRegistro;
import comun.TipoDatoBasico;

import dao.generico.GenericDao;

/**
 * Clase de acceso y manejo de los datos relacionados a los jugadores
 * 
 * @author Robert A
 * @author German L
 * @author Edgar L
 * @author Maria F
 * @author Miguel B
 * @version 0.2 10/01/2012
 * 
 */
public class DaoJugador extends GenericDao {

	/**
	 * Guarda los datos de un jugador en las correspondintes entidades que lo
	 * conforman
	 * 
	 * @param c
	 *            Objeto Jugador a ser guardado
	 */
	public void guardar(Jugador c) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.save(c.getPersonaNatural().getPersona());
		session.save(c.getPersonaNatural());
		session.save(c);
		tx.commit();
	}

	/**
	 * Actualiza los datos de un jugador en las correspondintes entidades que lo
	 * conforman
	 * 
	 * @param c
	 *            Objeto Jugador a ser actualizado
	 */
	public void actualizar(Jugador c) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.merge(c.getPersonaNatural().getPersona());
		session.merge(c.getPersonaNatural());
		session.merge(c);
		tx.commit();
	}

	/**
	 * @param jugador
	 *            El jugador que desea retirarse
	 */
	public void retirar(Jugador jugador) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();

		Criteria c = session.createCriteria(PersonaNatural.class).add(
				Restrictions.eq("cedulaRif", jugador.getCedulaRif()));

		PersonaNatural personaN = (PersonaNatural) c.uniqueResult();
		personaN.setEstatus('E');
		session.update(personaN);

		Criteria c2 = session.createCriteria(Persona.class).add(
				Restrictions.eq("cedulaRif", jugador.getCedulaRif()));

		Persona persona = (Persona) c2.uniqueResult();
		persona.setEstatus('E');
		session.update(persona);

		Criteria c3 = session.createCriteria(Jugador.class).add(
				Restrictions.eq("cedulaRif", jugador.getCedulaRif()));

		Jugador jugador2 = (Jugador) c3.uniqueResult();
		jugador2.setEstatus('E');
		session.update(jugador2);

		Criteria c4 = session.createCriteria(Roster.class)
				.add(Restrictions.eq("jugador", jugador))
				.add(Restrictions.eq("estatus", 'A'));

		Roster roster = (Roster) c4.uniqueResult();
		if (roster != null) {
			roster.setEstatus('E');
			session.update(roster);
		}
		tx.commit();
	}

	/**
	 * Busca los jugadores segun los filtros seleccionados
	 * 
	 * @param filtro2
	 *            filtro de la cedula
	 * @param filtro3
	 *            filtro del primer nombre
	 * @param filtro4
	 *            filtro del primer apellido
	 * @param filtro1
	 *            filtro del numero de camiseta
	 * @param estatus
	 *            estatus del registro
	 * @return List de Jugadores filtrados
	 */
	public List<Jugador> buscarJugadores(String filtro2, String filtro3,
			String filtro4, String filtro1, char estatus) {
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		List<Jugador> lista = new ArrayList<Jugador>();
		Criteria c = null;
		switch (estatus) {
		case 'E':
			c = session.createCriteria(Jugador.class)
					.add(Restrictions.eq("estatus", estatus))
					.add(Restrictions.like("cedulaRif", filtro2 + "%"));
			if (filtro1 != "") {
				c.add(Restrictions.eq("numero", Integer.valueOf(filtro1)));
			}
			c.createCriteria("personaNatural")
					.add(Restrictions.like("primerNombre", filtro3 + "%"))
					.add(Restrictions.like("primerApellido", filtro4 + "%"));
			List<Jugador> lista2 = c.list();
			Criteria c1 = session.createCriteria(RetiroTraslado.class).add(
					Restrictions.eq("estatus", 'A'));
			List<RetiroTraslado> lista1 = c1.list();

			for (int i = 0; i < lista2.size(); i++) {
				for (int j = 0; j < lista1.size(); j++) {
					if (lista2.get(i).getCedulaRif()
							.equals(lista1.get(j).getJugador().getCedulaRif())) {
						lista.add(lista2.get(i));
					}
				}
			}
			break;
		case 'R':
			DatoBasico tipoLapso = new DaoDatoBasico().buscarTipo(
					TipoDatoBasico.TIPO_LAPSO_DEPORTIVO, "TEMPORADA REGULAR");
			LapsoDeportivo lapsoDeportivo = new LapsoDeportivo();

			Criteria cLapsoDeportivo = session
					.createCriteria(LapsoDeportivo.class)
					.add(Restrictions.eq("datoBasico", tipoLapso))
					.add(Restrictions.eq("estatus", 'A'));
			lapsoDeportivo = (LapsoDeportivo) cLapsoDeportivo.uniqueResult();
			if (lapsoDeportivo != null) {
				c = session
						.createCriteria(Jugador.class)
						.add(Restrictions.eq("estatus", EstatusRegistro.ACTIVO))
						.add(Restrictions.not(Restrictions.between(
								"fechaInscripcion",
								lapsoDeportivo.getFechaInicio(),
								lapsoDeportivo.getFechaFin())))
						.add(Restrictions.like("cedulaRif", filtro2 + "%"));
				if (filtro1 != "") {
					c.add(Restrictions.eq("numero", Integer.valueOf(filtro1)));
				}
				c.createCriteria("personaNatural")
						.add(Restrictions.like("primerNombre", filtro3 + "%"))
						.add(Restrictions.like("primerApellido", filtro4 + "%"));
				lista = c.list();
			}
			break;
		default:
			c = session.createCriteria(Jugador.class)
					.add(Restrictions.eq("estatus", estatus))
					.add(Restrictions.like("cedulaRif", filtro2 + "%"));
			if (filtro1 != "") {
				c.add(Restrictions.eq("numero", Integer.valueOf(filtro1)));
			}
			c.createCriteria("personaNatural")
					.add(Restrictions.like("primerNombre", filtro3 + "%"))
					.add(Restrictions.like("primerApellido", filtro4 + "%"));
			lista = c.list();

			break;
		}
		return lista;
	}

	/**
	 * @return numero de registros (+1) en jugador con la el comodin 'R'
	 */
	public int generarCodigoTemporal() {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		int cantidad = 0;
		Criteria criteria = session.createCriteria(Jugador.class);
		criteria.setProjection(Projections.rowCount()).add(
				Restrictions.like("cedulaRif", "R-%"));
		cantidad = (Integer) criteria.list().get(0) + 1;
		tx.commit();
		return cantidad;
	}

	/**
	 * Actualiza los datos asociados al jugador, a su nuevo identificador
	 * (cedula generada)
	 * 
	 * @param jugador
	 *            datos del jugador
	 * @return cedula generada
	 */
	public String actualizarDatosJugador(Jugador jugador) {
		DaoFamiliarJugador daoFJ = new DaoFamiliarJugador();
		Familiar representante = daoFJ.buscarRepresentanteActual(jugador
				.getCedulaRif());
		String nuevaCedula = "";
		String cedulaActual = "";
		String secuencia = "";
		Transaction tx = null;
		if (representante != null) {
			cedulaActual = jugador.getCedulaRif();
			secuencia = String.valueOf(daoFJ.generarSecuencia(representante));
			nuevaCedula = representante.getCedulaRif() + "-" + secuencia;
			try {
				String inserts[] = {
						"INSERT INTO persona (SELECT '"
								+ nuevaCedula
								+ "', codigo_tipo_persona, codigo_parroquia, telefono_habitacion, "
								+ "fecha_ingreso, correo_electronico, twitter, direccion, fecha_egreso, estatus"
								+ " FROM persona WHERE cedula_rif='"
								+ cedulaActual + "')",
						"INSERT INTO persona_natural (SELECT '"
								+ nuevaCedula
								+ "', codigo_genero, celular, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido,"
								+ " fecha_nacimiento, foto,estatus "
								+ "FROM persona_natural WHERE cedula_rif='"
								+ cedulaActual + "' )",
						"INSERT INTO jugador (SELECT '"
								+ nuevaCedula
								+ "', codigo_pais, codigo_parroquia_nacimiento, numero, "
								+ "tipo_de_sangre, peso, altura, brazo_lanzar, posicion_bateo, "
								+ "estatus, fecha_inscripcion FROM jugador WHERE "
								+ "cedula_rif='" + cedulaActual + "')" };
				String updates[] = {
						"UPDATE roster SET cedula_rif='" + nuevaCedula
								+ "' WHERE cedula_rif='" + cedulaActual + "'",
						"UPDATE dato_medico SET cedula_rif='" + nuevaCedula
								+ "' WHERE cedula_rif='" + cedulaActual + "'",
						"UPDATE dato_academico SET cedula_rif='" + nuevaCedula
								+ "' WHERE cedula_rif='" + cedulaActual + "'",
						"UPDATE dato_social SET cedula_rif='" + nuevaCedula
								+ "' WHERE cedula_rif='" + cedulaActual + "'",
						"UPDATE talla_por_jugador SET cedula_rif='"
								+ nuevaCedula + "' WHERE cedula_rif='"
								+ cedulaActual + "'",
						"UPDATE documento_personal SET cedula_rif='"
								+ nuevaCedula + "' WHERE cedula_rif='"
								+ cedulaActual + "'",
						"UPDATE familiar_jugador SET cedula_rif='"
								+ nuevaCedula + "' WHERE cedula_rif='"
								+ cedulaActual + "'" };
				String deletions[] = {
						"DELETE FROM jugador WHERE " + "cedula_rif='"
								+ cedulaActual + "'",
						"DELETE FROM persona_natural WHERE " + "cedula_rif='"
								+ cedulaActual + "'",
						"DELETE FROM persona WHERE " + "cedula_rif='"
								+ cedulaActual + "'" };

				Session session = getSession();
				tx = session.beginTransaction();
				SQLQuery sqlQuery = null;
				// Duplicamos los datos del jugador
				for (String insert : inserts) {
					sqlQuery = session.createSQLQuery(insert);
					sqlQuery.executeUpdate();
				}
				// Actualizamos claves
				for (String update : updates) {
					sqlQuery = session.createSQLQuery(update);
					sqlQuery.executeUpdate();
				}
				// Eliminamos el registro con cedula temporal
				for (String delete : deletions) {
					sqlQuery = session.createSQLQuery(delete);
					sqlQuery.executeUpdate();
				}
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null) {
					tx.rollback();
				}
			}
		}
		return nuevaCedula;
	}

	/**
	 * Actualiza todos los registros del jugador con la nueva cédula
	 * 
	 * @param jugador
	 *            Objeto Jugador seleccionado y la nueva cédula del jugador
	 */
	public void cambiarCedula(String nuevaCedula, Jugador jugador) {
		String cedulaActual = "";
		Transaction tx = null;
		cedulaActual = jugador.getCedulaRif();
		try {
			String inserts[] = {
					"INSERT INTO persona (SELECT '"
							+ nuevaCedula
							+ "', codigo_tipo_persona, codigo_parroquia, telefono_habitacion,fecha_ingreso, correo_electronico, twitter, direccion, fecha_egreso, estatus FROM persona WHERE cedula_rif='"
							+ cedulaActual + "')",
					"INSERT INTO persona_natural (SELECT '"
							+ nuevaCedula
							+ "', codigo_genero, celular, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido,fecha_nacimiento, foto,estatus FROM persona_natural WHERE cedula_rif='"
							+ cedulaActual + "' )",
					"INSERT INTO jugador (SELECT '"
							+ nuevaCedula
							+ "', codigo_pais, codigo_parroquia_nacimiento, numero, tipo_de_sangre, peso, altura, brazo_lanzar, posicion_bateo, estatus, fecha_inscripcion FROM jugador WHERE cedula_rif='"
							+ cedulaActual + "')",
					"INSERT INTO jugador_plan (SELECT '"
							+ nuevaCedula
							+ "', codigo_tipo_jugador, codigo_talla_indumentaria,	apellido, nombre, estatus, fecha_nacimiento FROM jugador_plan WHERE cedula_rif='"
							+ cedulaActual + "')" };

			String updates[] = {
					"UPDATE roster SET cedula_rif='" + nuevaCedula
							+ "' WHERE cedula_rif='" + cedulaActual + "'",
					"UPDATE dato_medico SET cedula_rif='" + nuevaCedula
							+ "' WHERE cedula_rif='" + cedulaActual + "'",
					"UPDATE dato_academico SET cedula_rif='" + nuevaCedula
							+ "' WHERE cedula_rif='" + cedulaActual + "'",
					"UPDATE dato_social SET cedula_rif='" + nuevaCedula
							+ "' WHERE cedula_rif='" + cedulaActual + "'",
					"UPDATE talla_por_jugador SET cedula_rif='" + nuevaCedula
							+ "' WHERE cedula_rif='" + cedulaActual + "'",
					"UPDATE documento_personal SET cedula_rif='" + nuevaCedula
							+ "' WHERE cedula_rif='" + cedulaActual + "'",
					"UPDATE documento_acreedor SET cedula_atleta='"
							+ nuevaCedula + "' WHERE cedula_atleta='"
							+ cedulaActual + "'",
					"UPDATE familiar_jugador SET cedula_rif='" + nuevaCedula
							+ "' WHERE cedula_rif='" + cedulaActual + "'",
					"UPDATE dato_deportivo SET cedula_rif='" + nuevaCedula
							+ "'WHERE cedula_rif='" + cedulaActual + "'",
					"UPDATE retiro_traslado SET cedula_rif='" + nuevaCedula
							+ "' WHERE cedula_rif='" + cedulaActual + "'",
					"UPDATE dato_conducta SET cedula_rif='" + nuevaCedula
							+ "' WHERE cedula_rif='" + cedulaActual + "'",
					"UPDATE roster_plan SET cedula_rif='" + nuevaCedula
							+ "' WHERE cedula_rif='" + cedulaActual + "'",
					"UPDATE representante_jugador_plan SET cedula_jugador='"
							+ nuevaCedula + "' WHERE cedula_jugador='"
							+ cedulaActual + "'" };

			String deletions[] = {
					"DELETE FROM jugador_plan WHERE cedula_rif='"
							+ cedulaActual + "'",
					"DELETE FROM jugador WHERE cedula_rif='" + cedulaActual
							+ "'",
					"DELETE FROM persona_natural WHERE cedula_rif='"
							+ cedulaActual + "'",
					"DELETE FROM persona WHERE cedula_rif='" + cedulaActual
							+ "'" };

			Session session = getSession();
			tx = session.beginTransaction();
			SQLQuery sqlQuery = null;
			// Duplica los datos del jugador
			for (String insert : inserts) {
				sqlQuery = session.createSQLQuery(insert);
				sqlQuery.executeUpdate();
			}
			// Actualiza claves
			for (String update : updates) {
				sqlQuery = session.createSQLQuery(update);
				sqlQuery.executeUpdate();
			}
			// Elimina el registro con cédula temporal
			for (String delete : deletions) {
				sqlQuery = session.createSQLQuery(delete);
				sqlQuery.executeUpdate();
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
		}
	}

}