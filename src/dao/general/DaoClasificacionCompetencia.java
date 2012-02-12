package dao.general;

import java.util.List;

import modelo.ClasificacionCompetencia;
import modelo.DatoBasico;
import modelo.Estadio;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

/**
 * @author Merielen Gaspar
 * @author Diana Santiago
 * @version 2.0 01/02/2012
 * 
 */


public class DaoClasificacionCompetencia extends GenericDao {

	/**
	 * Clase DAO ClasificacionCompetencia para acceso/manejo de las clasificaciones de competencias.
	 * Proporciona metodos para ser implementados en un ServicioClasificacionCompetencia.
	 * 
	 */

	public List<ClasificacionCompetencia> listarActivos(){

		/**
		 * Metodo que permite listar las Clasificaciones de una Competencia
		 * especifica, de acuerdo a su estatus.
		 * 
		 * @return List<ClasificacionCompetencia> Lista que contiene las Clasificaciones de una Competencia especifica.
		 * 
		 */

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(ClasificacionCompetencia.class);
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}
	
	
	public List<ClasificacionCompetencia> listarClasificacionPorFiltro(String dato) {
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Query query = session.createSQLQuery(
				"select *from clasificacion_competencia,dato_basico where clasificacion_competencia.nombre like '" +dato+  "%' and clasificacion_competencia.tipo_competencia = dato_basico.codigo_dato_basico and clasificacion_competencia.estatus = 'A' " +
						"or clasificacion_competencia.descripcion like '" +dato+  "%' and clasificacion_competencia.tipo_competencia = dato_basico.codigo_dato_basico and clasificacion_competencia.estatus = 'A' " +
								"or dato_basico.nombre like '" +dato+  "%' and clasificacion_competencia.tipo_competencia = dato_basico.codigo_dato_basico and clasificacion_competencia.estatus = 'A'").addEntity(ClasificacionCompetencia.class);
		
		List<ClasificacionCompetencia> lista = query.list();
		
		System.out.println(lista.size());
		return lista;
	}
	
	public List<ClasificacionCompetencia> listarClasificacion(DatoBasico cl){

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(ClasificacionCompetencia.class);
		c.add(Restrictions.eq("estatus",'A'));
		c.add(Restrictions.eq("datoBasico", cl));
		return c.list();
	}	

}
