package dao.general;

import java.util.List;

import dao.generico.GenericDao;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.EscalaMedicion;
import modelo.Indicador;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoIndicador extends GenericDao {

	public List listarActivos(Class o) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		List lista = session.createCriteria(o)
				.add(Restrictions.eq("estatus", "A")).list();
		return lista;
	}
	
	public List listarActivosYSistema(Class o) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();		
		List lista = session.createCriteria(o).add(Restrictions.or(Restrictions.eq("estatus", "A"), Restrictions.eq("estatus", "S"))).list();
		return lista;
	}

	public List<Indicador> buscarPorDatoBasico(DatoBasico datoBasico) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = getSession().createCriteria(Indicador.class);
		criteria.add(Restrictions.eq("datoBasicoByCodigoModalidad", datoBasico));
		criteria.add(Restrictions.eq("estatus", 'A'));
		return criteria.list();
	}
	
	public List <Indicador> listarIndicadorIndividualPorModalidad (DatoBasico modalidad){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(DatoBasico.class);
		criteria.add(Restrictions.eq("nombre","INDIVIDUAL"));
		DatoBasico medicion = (DatoBasico) criteria.list().get(0);
		criteria = session.createCriteria(Indicador.class);
		criteria.add(Restrictions.eq("datoBasicoByCodigoModalidad", modalidad));
		criteria.add(Restrictions.eq("datoBasicoByCodigoMedicion", medicion));
		criteria.add(Restrictions.eq("estatus", 'A'));
		return criteria.list();				
	}
	
	public List <Indicador> listarIndicadorColectivoPorModalidad (DatoBasico modalidad){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(DatoBasico.class);
		criteria.add(Restrictions.eq("nombre","COLECTIVO"));
		DatoBasico medicion = (DatoBasico) criteria.list().get(0);
		criteria = session.createCriteria(Indicador.class);
		criteria.add(Restrictions.eq("datoBasicoByCodigoModalidad", modalidad));
		criteria.add(Restrictions.eq("datoBasicoByCodigoMedicion", medicion));
		criteria.add(Restrictions.eq("estatus", 'A'));
		return criteria.list();				
	}
	
	public List<Indicador> listarIndicadoresPorFiltro(String dato) {
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Query query = session
				.createSQLQuery(								
								"select * from indicador  where indicador.nombre like '"
								+ dato
								+ "%'" //and indicador.codigo_medicion = dato_basico.codigo_dato_basico and indicador.codigo_modalidad = dato_basico.codigo_dato_basico and indicador.codigo_tipo_indicador = dato_basico.codigo_dato_basico  " )
								
								+ "or indicador.abreviatura like '"
								+ dato
								+ "%'") // and indicador.codigo_medicion = dato_basico.codigo_dato_basico and codigo_modalidad = dato_basico.codigo_dato_basico and codigo_tipo_indicador = dato_basico.codigo_dato_basico  ")
				.addEntity(Indicador.class);

		List<Indicador> lista = query.list();

		System.out.println(lista.size());
		return lista;
	}
	
	public List <Indicador> listarIndicadorPorModalidadyTipo (DatoBasico modalidad, String m){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(DatoBasico.class);
		criteria.add(Restrictions.eq("nombre",m));
		DatoBasico medicion = (DatoBasico) criteria.list().get(0);
		criteria = session.createCriteria(Indicador.class);
		criteria.add(Restrictions.eq("datoBasicoByCodigoModalidad", modalidad));
		criteria.add(Restrictions.eq("datoBasicoByCodigoMedicion", medicion));
		criteria.add(Restrictions.eq("estatus", 'S'));
		return criteria.list();				
	}

	
	
	
}
