package dao.general;

import java.util.List;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.Estadio;
import modelo.Juego;
import modelo.LapsoDeportivo;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import comun.EstadoCompetencia;

import dao.generico.GenericDao;
import dao.generico.SessionManager;

/**
 * DAO para la clase Competencia
 * 
 * @author Nohely Pernalete
 * @author Gabrielena Ponce
 * @version 1.0
 * 
 */
public class DaoCompetencia extends GenericDao {

	/**
	 * Permite listar las competencias de acuerdo a su estatus
	 * 
	 * @param estatus
	 *            Estatus de la competencia
	 * @return List<Competencia> Lista de Competencias
	 */

	public List<Competencia> listarPorEstatus(int estatus) {

		/*
		 * Buscamos en la tabla DatoBasico el objeto correspondiente para el
		 * estatus de competencia especificado. Luego con este objeto buscamos
		 * en la tabla Competencia.
		 */
		// Session session = getSession();
		Session session = SessionManager.getSession();
		Transaction tx = session.beginTransaction();

		Criteria c = session.createCriteria(DatoBasico.class);

		c.add(Restrictions.eq("codigoDatoBasico", estatus));
		DatoBasico db = (DatoBasico) c.list().get(0);

		c = session.createCriteria(Competencia.class);
		c.add(Restrictions.eq("datoBasicoByCodigoEstadoCompetencia", db));
		c.add(Restrictions.eq("estatus", 'A'));
		return c.list();

	}

	public List<Competencia> listarRegistradasAperturadas() {

		Session session = getSession();
		Transaction tx = session.beginTransaction();

		Criteria c = session.createCriteria(DatoBasico.class);

		c.add(Restrictions.eq("codigoDatoBasico", EstadoCompetencia.REGISTRADA));
		DatoBasico dbr = (DatoBasico) c.list().get(0);

		c = session.createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("codigoDatoBasico", EstadoCompetencia.APERTURADA));
		DatoBasico dba = (DatoBasico) c.list().get(0);

		Criteria q = session.createCriteria(Competencia.class);
		Criterion cr1 = Restrictions.eq("datoBasicoByCodigoEstadoCompetencia",
				dbr);
		Criterion cr2 = Restrictions.eq("datoBasicoByCodigoEstadoCompetencia",
				dba);
		q.add(Restrictions.or(cr1, cr2));

		return q.list();

	}
	
	public List<Competencia> listarAperturadasClausurada() {

		Session session = getSession();
		Transaction tx = session.beginTransaction();

		Criteria c = session.createCriteria(DatoBasico.class);

		c.add(Restrictions.eq("codigoDatoBasico", EstadoCompetencia.APERTURADA));
		DatoBasico dba = (DatoBasico) c.list().get(0);
		
		c = session.createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("codigoDatoBasico", EstadoCompetencia.CLAUSURADA));
		DatoBasico dbr = (DatoBasico) c.list().get(0);

		Criteria q = session.createCriteria(Competencia.class);
		Criterion cr1 = Restrictions.eq("datoBasicoByCodigoEstadoCompetencia",
				dbr);
		Criterion cr2 = Restrictions.eq("datoBasicoByCodigoEstadoCompetencia",
				dba);
		q.add(Restrictions.or(cr1, cr2));

		return q.list();

	}

	public List<Competencia> listarCompetenciasPorFiltro(String dato) {
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Query query = session
				.createSQLQuery(
						"select *from competencia,clasificacion_competencia,dato_basico  where competencia.nombre like '"
								+ dato
								+ "%' and competencia.codigo_clasificacion_competencia = clasificacion_competencia.codigo_clasificacion_competencia and clasificacion_competencia.tipo_competencia = dato_basico.codigo_dato_basico  "
								+ "or clasificacion_competencia.nombre like '"
								+ dato
								+ "%' and competencia.codigo_clasificacion_competencia = clasificacion_competencia.codigo_clasificacion_competencia and clasificacion_competencia.tipo_competencia = dato_basico.codigo_dato_basico  "
								+ "or dato_basico.nombre like '"
								+ dato
								+ "%' and competencia.codigo_clasificacion_competencia = clasificacion_competencia.codigo_clasificacion_competencia and clasificacion_competencia.tipo_competencia = dato_basico.codigo_dato_basico")
				.addEntity(Competencia.class);

		List<Competencia> lista = query.list();

		System.out.println(lista.size());
		return lista;
	}
	
	public List<Competencia> listarCompetenciasPorFiltro(String dato,int estadoComp) {
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Query query = session.createSQLQuery(
				"select *from competencia,clasificacion_competencia,dato_basico  where competencia.nombre like '" +dato+  "%' and competencia.codigo_clasificacion_competencia = clasificacion_competencia.codigo_clasificacion_competencia and clasificacion_competencia.tipo_competencia = dato_basico.codigo_dato_basico and competencia.codigo_estado_competencia = '" +estadoComp+  "' and competencia.estatus='A'  " +
						"or clasificacion_competencia.nombre like '" +dato+  "%' and competencia.codigo_clasificacion_competencia = clasificacion_competencia.codigo_clasificacion_competencia and clasificacion_competencia.tipo_competencia = dato_basico.codigo_dato_basico  and competencia.codigo_estado_competencia = '" +estadoComp+  "' and competencia.estatus='A'  "+
		"or dato_basico.nombre like '" +dato+  "%' and competencia.codigo_clasificacion_competencia = clasificacion_competencia.codigo_clasificacion_competencia and clasificacion_competencia.tipo_competencia = dato_basico.codigo_dato_basico and competencia.codigo_estado_competencia = '" +estadoComp+  "' and competencia.estatus='A' ").addEntity(Competencia.class); 

		
		List<Competencia> lista = query.list();
		
		System.out.println(lista.size());
		return lista;
	}
	
	public List<Competencia> buscarCompetencias(LapsoDeportivo lapso,
			DatoBasico db, DatoBasico db1) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria where = getSession().createCriteria(Competencia.class);
		where.add(Restrictions.eq("lapsoDeportivo", lapso));
		where.add(Restrictions.not(Restrictions.eq("datoBasicoByCodigoEstadoCompetencia", db)));
		where.add(Restrictions.not(Restrictions.eq("datoBasicoByCodigoEstadoCompetencia", db1)));
		where.add(Restrictions.eq("estatus", 'A'));
		return where.list();
	}

	
	public List<Competencia> buscarCompetenciaPorNombre(String nombre_comp, LapsoDeportivo lapso) {
		
		int lapso2 = lapso.getCodigoLapsoDeportivo();
		
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Query query = session.createSQLQuery(
				"select *from competencia where nombre = '" +nombre_comp+  "' and codigo_lapso_deportivo = '" +lapso2+  "' and estatus = 'A' ").addEntity(Competencia.class); 
		
		List<Competencia> lista = query.list();
		
		System.out.println(lista.size());
		return lista;	
		
	}
}
