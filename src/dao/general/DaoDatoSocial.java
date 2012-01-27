package dao.general;

import java.util.ArrayList;
import java.util.List;

import modelo.AfeccionJugador;
import modelo.DatoMedico;
import modelo.DatoSocial;
import modelo.Jugador;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

/**
 * Clase de acceso y manejo de los datos relacionados a los datos sociales del
 * jugador
 * 
 * @author Robert A
 * @author German L
 * @version 0.1  10/01/2012
 * 
 */
public class DaoDatoSocial extends GenericDao {
	public static String SECUENCIA = "dato_social_codigo_dato_social_seq_1";

	/**
	 * Guarda los datos de las actividades sociales (extra-academicas) en las
	 * que participa un jugador
	 * 
	 * @param datos
	 *            lista de actividades sociales
	 */
	public void guardar(List<DatoSocial> datos) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		for (DatoSocial dato : datos) {
			session.save(dato);
		}
		tx.commit();
	}

	/**
	 * Actualiza la lista de datos sociales asociadas a un  jugador
	 * en base a la lista sumnistrada
	 * 
	 * @param datos
	 *            lista de datos a actualizar
	 * @param jugador
	 *            jugador asociado a los datos  sociales a actualizar
	 */

	public void actualizar( List<DatoSocial> datosSoc, Jugador jugador) {
		Session sesion = getSession();
		Transaction tx = sesion.beginTransaction();
		int p = 0;
		 List<DatoSocial> datos= new ArrayList<DatoSocial>();
		 datos.addAll(datosSoc);
		Criteria c = sesion.createCriteria(DatoSocial.class)
				.createCriteria("jugador")
				.add(Restrictions.eq("cedulaRif", jugador.getCedulaRif()));
		List<DatoSocial> datosAlmacenados = (List<DatoSocial>) c.list();
		for (DatoSocial datoAlmacenado : datosAlmacenados) {
			p = buscarDatoPorCodigo(datos, datoAlmacenado);
			if (p == -1) {
				datoAlmacenado.setEstatus('E');
			} else {
				datoAlmacenado.setFechaInicio(datos.get(p).getFechaInicio());
				datoAlmacenado.setHorasDedicadas(datos.get(p)
						.getHorasDedicadas());
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
		for (DatoSocial datoNuevo : datos) {
			sesion.save(datoNuevo);
		}

		tx.commit();
	}

	/**
	 * Busca un dato social dentro de la lista por su codigo
	 * 
	 * @param datoJugador
	 *            List de datos sociales
	 * @param datoBuscar
	 *            Dato social buscado
	 * @return posicion del dato buscado, en caso de no encontrarlo retorna -1
	 */
	private int buscarDatoPorCodigo(List<DatoSocial> datos,
			DatoSocial datoBuscar) {
		int posicion = -1;
		int i = 0;
		for (DatoSocial datoJugador : datos) {
			if (datoJugador.getDatoBasico().getCodigoDatoBasico() == datoBuscar
					.getDatoBasico().getCodigoDatoBasico()
					&& datoJugador.getInstitucion().getCodigoInstitucion() == datoBuscar
							.getInstitucion().getCodigoInstitucion()) {
				posicion = i;
				break;
			}
			i++;
		}
		return posicion;
	}
}
