package dao.general;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.AfeccionJugador;
import modelo.DatoMedico;


import dao.generico.GenericDao;

/**
 * Clase de acceso y manejo de los datos relacionados a las afecciones de los
 * jugadores
 * 
 * @author Robert A
 * @author German L
 * @version 0.1.2 10/01/2011
 * 
 */
public class DaoAfeccionJugador extends GenericDao {

	/**
	 * Guarda las afecciones que padece un jugador
	 * 
	 * @param afecciones
	 *            Lista de afecciones padecidas por un jugador
	 */
	public void guardar(List<AfeccionJugador> afecciones) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		for (AfeccionJugador afeccionJugador : afecciones) {
			session.save(afeccionJugador);
		}
		tx.commit();
	}

	/**
	 * Actualiza la lista de afecciones asociadas a un dato medico del jugador en base a la lista sumnistrada
	 * @param afecciones lista de afecciones a actualizar 
	 * @param datoMedico dato medico asociado a las  afeciones almacenadas a actualizar
	 */
	public void actualizar(List<AfeccionJugador> afeccionesJug,
			DatoMedico datoMedico) {
		int codigoDatoMedico = datoMedico.getCodigoDatoMedico();
		int p = 0;
		Session sesion = getSession();
		Transaction tx = sesion.beginTransaction();
		List<AfeccionJugador> afecciones= new ArrayList<AfeccionJugador>();
		 afecciones.addAll(afeccionesJug);
		Criteria c = sesion.createCriteria(AfeccionJugador.class)
				.createCriteria("datoMedico")
				.add(Restrictions.eq("codigoDatoMedico", codigoDatoMedico));
		List<AfeccionJugador> afeccionesAlmacenadas = (List<AfeccionJugador>) c.list();
		for (AfeccionJugador afeccionAlmacenada : afeccionesAlmacenadas) {
			p = buscarAfeccionPorCodigo(afecciones, afeccionAlmacenada
					.getDatoBasico().getCodigoDatoBasico());
			if (p == -1) {
				afeccionAlmacenada.setEstatus('E');
				sesion.update(afeccionAlmacenada);
			} else {
				/*
				 * Actualizamos, los valores que se hayan modificado, en este
				 * caso no hay valores que puedan ser act., asi que se omite este
				 * paso
				 */
				if (afeccionAlmacenada.getEstatus()=='E'){
					afeccionAlmacenada.setEstatus('A');
					sesion.update(afeccionAlmacenada);
				}
				afecciones.remove(p);
			}
		}
		/*
		 * Procesamos los valores que hayan quedado en la lista afecciones,
		 * estos restantes representan las nuevas afecciones que ha agregado el
		 * usuario
		 */
		for (AfeccionJugador afeccionNueva : afecciones) {
			sesion.save(afeccionNueva);
		}
		tx.commit();

	}

	/**
	 * Busca una afeccion dentro de la lista por su codigo
	 * @param afecciones List de afecciones
	 * @param codigoAfeccion identificador de la afeccion buscada
	 * @return posicion de la afeccion buscada, en caso de no encontrarla retorna -1
	 */
	private int buscarAfeccionPorCodigo(List<AfeccionJugador> afecciones,
			int codigoAfeccion) {
		int posicion = -1;
		int i = 0;
		for (AfeccionJugador afeccionJugador : afecciones) {
			if (afeccionJugador.getDatoBasico().getCodigoDatoBasico() == codigoAfeccion) {
				posicion = i;
				break;
			}
			i++;
		}
		return posicion;
	}

}
