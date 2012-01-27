package dao.general;

import java.util.List;

import modelo.Categoria;
import modelo.Equipo;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;


/**
 * Clase de acceso y manejo de los datos relacionados a los equipos de la divisa
 * @author Robert A
 * @author German L
 * @version 0.1 20/12/2011
 *
 */
public class DaoEquipo extends GenericDao {
	
	public List listarEquipoPorCategoria(Class o, int codigo){
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(Equipo.class);
		c.add(Restrictions.eq("categoria.codigoCategoria",codigo));
		List <Equipo> lista = c.list(); 
		return lista;
	}
	
	public List listarEquipoForaneos(){
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(Equipo.class);
		c.add(Restrictions.not(Restrictions.eq("divisa.codigoDivisa",1)));
		List <Equipo> lista = c.list();	
		return lista;
	}
	

	/**
	 * Busca los equipos que pertenecen a una categoria dada
	 * @param categoria Categoria para filtrar los equipos 
	 * @return List<Equipo> Lista de equipos de una categoria
	 */
	public List<Equipo> buscarEquiposPorCategoria(Categoria categoria){
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session
				.createCriteria(Equipo.class)
				.add(Restrictions.eq("categoria", categoria))
				.add(Restrictions.eq("estatus", 'A'));
		List<Equipo> lista =  c.list();
		return lista;
	}

	/****
	 * Busca los equipos que pertenecen a una categoria dada
	 * @param categoria Categoria para filtrar los equipos 
	 * @return List<Equipo> Lista de equipos de una categoria
	 */
	public List<Equipo> buscarEquiposForaneosPorCategoria(int categoria){
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session
				.createCriteria(Equipo.class)
				.add(Restrictions.eq("categoria.codigoCategoria", categoria))
				.add(Restrictions.eq("estatus", 'A'))
				.add(Restrictions.not(Restrictions.eq("divisa.codigoDivisa", 1)));
		List<Equipo> lista =  c.list();
		return lista;
	}
	
	/**
	 * Busca todos los equipos activos de todas las divisas
	 * @param o Class del modelo a filtrar
	 * @return List lista de equipos 
	 */
	public List listarActivos(Class o) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		List lista = session.createCriteria(o).add(Restrictions.eq("estatus", 'A')).list();
		return lista;
	}
	
	/**
	 * Agregado para ConfigurarEquipo
	 * @param equipo
	 * @return
	 */
	public boolean buscarPorCodigo(Equipo equipo) {
		boolean sw;
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Query query = session.createSQLQuery(
				"select equipo.codigo_equipo,roster.codigo_roster from equipo,roster where roster.estatus='A' and equipo.estatus='A'and equipo.codigo_equipo=roster.codigo_equipo and roster.codigo_equipo='"
						+ equipo.getCodigoEquipo() + "'").addEntity(Categoria.class);
		
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