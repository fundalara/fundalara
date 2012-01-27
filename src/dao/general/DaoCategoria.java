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
				.add(Restrictions.gt("edadInferior", edad))
				.add(Restrictions.gt("edadSuperior", edad))
				.add(Restrictions.eq("estatus", 'A'));
		return c.list();
	}
	
	/**
	 * Agregados para ConfigurarCategoria
	 */
	public List listar(Class o) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		List lista = session.createCriteria(o).add(Restrictions.eq("estatus", 'A')).list();
		return lista;
	}
	
	public boolean buscarPorCodigo(Categoria categoria) {
		//Categoria categoria;
		boolean sw;
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Query query = session.createSQLQuery(
				"select * from equipo,categoria where categoria.estatus='A' and equipo.estatus='A'and equipo.codigo_categoria=categoria.codigo_categoria and equipo.codigo_categoria='"
						+ categoria.getCodigoCategoria() + "'").addEntity(Categoria.class);
		
		List<Object> lista = query.list();
	
		if (lista.size()!=0) {
			sw=false;
		} else {
			sw=true;
		}
		tx.commit();
		return sw;
	}


}
