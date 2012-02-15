package dao.general;

import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;
import modelo.DatoBasico;
import modelo.Divisa;
import modelo.Equipo;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import comun.TipoDatoBasico;

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
	 * Busca los equipos que pertenecen a una categoria dada, de un tipo en
	 * espeficio de una divisa en particular
	 * 
	 * @param categoria
	 *            Categoria para filtrar los equipos
	 * @param lapsoDeportivo
	 *            lapso deportivo a las cual corresponden el equipo
	 * @param nombreDivisa
	 *            nomnre de la divisa a la que pertenecen los equipos
	 * @return List<Equipo> Lista de equipos de una categoria
	 */
	public List<Equipo> buscarEquiposPorCategoria(Categoria categoria,
			String lapsoDeportivo, String nombreDivisa) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		DaoDatoBasico daoDatobasico = new DaoDatoBasico();
		DatoBasico tipoLapso = daoDatobasico.buscarTipo(
				TipoDatoBasico.TIPO_LAPSO_DEPORTIVO, lapsoDeportivo);
		Criteria cDivisa = session.createCriteria(Divisa.class)
				.add(Restrictions.eq("nombre", nombreDivisa.toUpperCase()))
				.add(Restrictions.eq("estatus", 'A'));
		Divisa divisa = (Divisa) cDivisa.uniqueResult();
		Criteria c = session.createCriteria(Equipo.class)
				.add(Restrictions.eq("categoria", categoria))
				.add(Restrictions.eq("datoBasicoByCodigoTipoLapso", tipoLapso))
				.add(Restrictions.eq("divisa", divisa))
				.add(Restrictions.eq("estatus", 'A'));
		List<Equipo> lista = c.list();
		return lista;
	}

	public List<Equipo> buscarEquiposPorCategoria(Categoria categoria,
			String lapsoDeportivo) {
		return this.buscarEquiposPorCategoria(categoria, lapsoDeportivo,
				"FUNDALARA");
	}

	public List<Equipo> buscarEquiposPorCategoria(Categoria categoria) {
		return this.buscarEquiposPorCategoria(categoria, "TEMPORADA REGULAR");
	}

	/**
	 * Agregado para ConfigurarEquipo
	 * 
	 * @param equipo
	 * @return
	 */
	public boolean buscarPorCodigo(Equipo equipo) {
		boolean sw;
		Session session = SessionManager.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Query query = session
				.createSQLQuery(
						"select equipo.codigo_equipo,roster.codigo_roster from equipo,roster where roster.estatus='A' and equipo.estatus='A'and equipo.codigo_equipo=roster.codigo_equipo and roster.codigo_equipo='"
								+ equipo.getCodigoEquipo() + "'").addEntity(
						Categoria.class);
		List<Object> lista = query.list();
		if (lista.size() != 0) {
			sw = false;
		} else {
			sw = true;
		}
		tx.commit();
		return sw;
	}

	public List<Equipo> buscarEquiposDisponibles(Categoria categoria,
			String lapsoDeportivo, String nombreDivisa) {
		List<Equipo> equipos = new ArrayList<Equipo>();
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		DaoDatoBasico daoDatobasico = new DaoDatoBasico();
		DatoBasico tipoLapso = daoDatobasico.buscarTipo(
				TipoDatoBasico.TIPO_LAPSO_DEPORTIVO, lapsoDeportivo);
		nombreDivisa = nombreDivisa.toUpperCase();
		
		String sql = "SELECT e.codigo_equipo, e.codigo_tipo_lapso, e.codigo_clasificacion, e.codigo_categoria," 
				+ " e.codigo_divisa,e.nombre, e.estatus, e.minimo_jugador, e.maximo_jugador"
				+ " FROM  categoria c"
				+ " LEFT JOIN  equipo e ON (e.codigo_categoria=c.codigo_categoria AND e.estatus='A'"
				+ " AND e.codigo_tipo_lapso="+tipoLapso.getCodigoDatoBasico()+")"
				+ " LEFT JOIN roster r ON (r.codigo_equipo=e.codigo_equipo  AND r.estatus='A')"
				+ " LEFT JOIN divisa d ON (d.nombre='"+nombreDivisa+"' AND d.estatus='A')"
				+ " WHERE"
				+ " c.codigo_categoria="+categoria.getCodigoCategoria()+" AND c.estatus='A'"
				+ " GROUP BY e.codigo_equipo, e.nombre, e.maximo_jugador, "
				+ " e.codigo_equipo, e.codigo_tipo_lapso, e.codigo_clasificacion,"
				+ " e.codigo_categoria, e.codigo_divisa, e.nombre, e.estatus,"
				+ " e.minimo_jugador, e.maximo_jugador"
				+ " HAVING e.maximo_jugador-COUNT(codigo_roster) > 0 "
				+ " ORDER BY e.codigo_equipo";
		
		Query query = session.createSQLQuery(sql).addEntity(Equipo.class);
		equipos = query.list();

		return equipos;
	}
	
	public List<Equipo> buscarEquiposDisponibles(Categoria categoria,
			String lapsoDeportivo) {
		return this.buscarEquiposDisponibles(categoria, lapsoDeportivo,
				"FUNDALARA");
	}

	public List<Equipo> buscarEquiposDisponibles(Categoria categoria) {
		return this.buscarEquiposDisponibles(categoria, "TEMPORADA REGULAR");
	}

	public List<Equipo> listarPorTipo(Integer i) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Equipo.class);
		c.add(Restrictions
				.eq("datoBasicoByCodigoTipoLapso.codigoDatoBasico", i)).add(
				Restrictions.eq("estatus", 'A'));
		List<Equipo> lista = c.list();
		return lista;

	}

}