package dao.general;

import java.util.List;

import modelo.Competencia;
import modelo.FamiliarJugador;
import modelo.Hospedaje;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;
import dao.generico.SessionManager;

/**
 * Clase de acceso y manejo de los datos relacionados al Hospedaje
 * 
 * @author Erika O
 * @version 0.3 27-01-12
 * 
 */
public class DaoHospedaje extends GenericDao {

	/**
	 * Guarda los datos del hospedaje en la entidad correspondiente que lo
	 * conforma
	 * 
	 * @param c
	 *            Objeto Hospedaje a ser guardado
	 */
	public void guardar(Hospedaje c) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.save(c);
		tx.commit();
	}

	/**
	 * Actualiza los datos del hospedaje en las entidades correspondientes que
	 * lo conforman
	 * 
	 * @param h
	 *            Objeto Hospedaje a ser actualizado
	 * 
	 */
	public void actualizar(Hospedaje h) {
		Session session = SessionManager.getSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(h);
		tx.commit();
	}

	/**
	 * Método usado en Registrar Hospedaje, para listar los representantes y se
	 * pueda guardar en la grid los datos del familiarJugador necesarios
	 * 
	 * @param familiarJugador
	 *            a ser listado
	 * @return
	 */
	public List<Hospedaje> listarrepre(FamiliarJugador familiarJugador) {
		Session session = this.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Hospedaje.class)
				.add(Restrictions.eq("familiarJugador", familiarJugador))
				.add(Restrictions.eq("estatus", 'A'));
		return c.list();
	}

	/**
	 * Método usado en Registrar Hospedaje, para activar un registro eliminado
	 * 
	 * @param competencia
	 * @param familiarJugador
	 * @return
	 */
	public Hospedaje activar(Competencia competencia,
			FamiliarJugador familiarJugador) {
		Session session = this.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Hospedaje.class)
				.add(Restrictions.eq("familiarJugador", familiarJugador))
				.add(Restrictions.eq("competencia", competencia));
		Hospedaje hosp = new Hospedaje();
		return (Hospedaje) c.uniqueResult();
	}

	/**
	 * Método usado en Guardar de Registrar Hospedaje, verificando si el
	 * registro esta eliminado
	 * 
	 * @param competencia
	 * @param familiarJugador
	 * @return
	 */
	public boolean verificar(Competencia competencia,
			FamiliarJugador familiarJugador) {
		Session session = this.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Hospedaje.class)
				.add(Restrictions.eq("familiarJugador", familiarJugador))
				.add(Restrictions.eq("competencia", competencia));
		Hospedaje hosp = new Hospedaje();
		hosp = (Hospedaje) c.uniqueResult();
		if (hosp != null) {
			return true;
		} else {
			return false;
		}
	}

}