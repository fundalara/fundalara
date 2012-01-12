package dao.general;

import java.util.List;

import modelo.DatoBasico;
import modelo.Institucion;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;
import dao.generico.SessionManager;

/**
 * Clase de acceso y manejo de los datos relacionados a las instituciones
 * 
 * @author German L
 * @author Robert A
 * @version 0.1 20/12/2011
 * 
 */
public class DaoInstitucion extends GenericDao {

	/**
	 * Busca una institucion basado en su identificador
	 * 
	 * @param id
	 *            código de la institucion
	 * @return Institucion asociada al id o null en caso de no existir
	 */
	public Institucion buscar(String id) {
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Institucion.class);
		c.add(Restrictions.eq("codigoInstitucion", id));
		List list = c.list();
		if (list.size() > 0) {
			return (Institucion) list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Busca todas las instituciones de un tipo dado
	 * 
	 * @param datoBasico
	 *            tipo de institucion
	 * @return List de instituciones del tipo especificado
	 */
	public List<Institucion> buscarInstitucionTipo(DatoBasico datoBasico) {
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Query query = session
				.createSQLQuery(
						"SELECT * FROM institucion WHERE codigo_tipo_institucion="
								+ datoBasico.getCodigoDatoBasico()
								+ " AND estatus='A'").addEntity(
						Institucion.class);
		List<Institucion> lista = query.list();
		tx.commit();
		return lista;
	}

}
