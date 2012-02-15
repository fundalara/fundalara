package dao.general;
import java.util.List;
import modelo.Categoria;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import dao.generico.GenericDao;


/**
 * Clase de acceso y manejo de los datos relacionados a las categorias de los
 * jugadores
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 23/12/2011
 * 
 */
public class DaoCategoria extends GenericDao {
	public List<Categoria> listarActivos(){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Categoria.class);
		c.add(Restrictions.eq("estatus",'A'));
		return c.list();
	}
	public List<Categoria> listarcodigos(String nombreCategoria){
		//Permite buscar todas las divisas con estatus = 'A'
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(Categoria.class);
		List lista = c.add(Restrictions.eq("nombre",nombreCategoria)).list();
		
		return lista;
	}
	
	public Categoria BuscarCategoriaNombre (String nombre){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		
		Criteria criteria = getSession().createCriteria(Categoria.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		return (Categoria) criteria.uniqueResult();
	}
	
	/**
	 * Determina la categoria a la cual pertenece la edad suministrada
	 * 
	 * @param edad
	 *            edad del jugador
	 * @return Categoria que contiene la edad suministrada, en caso de no
	 *         existir se retorna null
	 */
	public Categoria buscarPorEdad(int edad) {
		Session session = this.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Categoria.class)
				.add(Restrictions.le("edadInferior", edad))
				.add(Restrictions.ge("edadSuperior", edad))
				.add(Restrictions.eq("estatus", 'A'));
		return (Categoria) c.uniqueResult();
	}

	public List<Categoria> buscarCategoriasPorEdad(int edad) {
		Session session = this.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Categoria.class)
				.add(Restrictions.le("edadInferior", edad))
				.add(Restrictions.eq("estatus", 'A'));
		return c.list();
	}

	/**
	 * Busca las categorias superiores a la que le corresponde a un jugador
	 * 
	 * @param edad
	 *            del jugador
	 * @return Lista de categorias superiores sino null
	 */
	public List<Categoria> buscarCategorias(int edad) {
		Session session = this.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Categoria.class)
				.add(Restrictions.ge("edadSuperior", edad))
				.add(Restrictions.eq("estatus", 'A'));
		List<Categoria> listCategoria = c.list();
		if (listCategoria.size() > 1) {
			listCategoria = listCategoria.subList(0, 2);
		}
		return listCategoria;
	}

	/**
	 * Busca todas las categorias disponibles al momento
	 * @param o Clase usada para consultas
	 * @return lista de las categorias disponibles al momentos 
	 */
	public List listar(Class o) {
		// Session session = getSession();
		Session session = SessionManager.getSession();
		Transaction tx = session.beginTransaction();
		List lista = session.createCriteria(o).list();
		return lista;
	}

	public boolean buscarPorCodigo(Categoria categoria) {
		// Categoria categoria;
		boolean sw;
		Session session = SessionManager.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Query query = session
				.createSQLQuery(
						"select * from equipo,categoria where categoria.estatus='A' and equipo.estatus='A'and equipo.codigo_categoria=categoria.codigo_categoria and equipo.codigo_categoria='"
								+ categoria.getCodigoCategoria() + "'")
				.addEntity(Categoria.class);

		List<Object> lista = query.list();

		if (lista.size() != 0) {
			sw = false;
		} else {
			sw = true;
		}
		tx.commit();
		return sw;
	}
	
	/**
	 * Busca las categorias superiores a la que le corresponden a un jugador
	 * para el ascenso
	 * @param edad
	 *            del jugador
	 * @return Lista de categorias superiores sino null
	 */
	public List<Categoria> buscarCategoriasAscenso(int edad) {
		Session session = this.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Categoria.class)
 				.add(Restrictions.gt("edadInferior", edad))
				.add(Restrictions.gt("edadSuperior", edad))
				.add(Restrictions.eq("estatus", 'A'));
		List<Categoria> listCategoria = c.list();
		if (listCategoria.size() > 1) {
			listCategoria = listCategoria.subList(0, 2);
		}
		return listCategoria;
	}

}
