package dao.general;

import java.util.List;

/** 
 * DAO para la clase Estadio
 * @author Alix Villanueva
 * @version 1.0
 *  
 */

import modelo.Competencia;
import modelo.Divisa;
import modelo.EquipoCompetencia;
import modelo.Estadio;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


public class DaoEstadio extends dao.generico.GenericDao{

	/**
	 * Permite listar los Estadios de acuerdo a su estatus
	 * 
	 * @param estatus Estatus del Estadio
	 * @return List<Estadios> Lista de Estadios
	 */
	
	public List<Estadio> listarActivos(){
		/* 
		 * Buscamos en la tabla estadio todo los estadios con estatus 'A' (Activo)
		 * y se listan.
		 */
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Estadio.class);
		c.add(Restrictions.eq("estatus",'A'));
		return c.list();
	}
	
	public List<Estadio> filtar(String cad){
			
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			Criteria c = session.createCriteria(Estadio.class);
			c.add(Restrictions.like("nombre", cad));
			return c.list();
			
		}
	
	
	public List listarEstadiosPorCompetencia(Competencia competencia,Estadio estadio){
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(Estadio.class);
		Criteria c1 = c.createCriteria("datoBasico");
		Criteria c2 = c1.createCriteria("datoBasico");
		Criteria c3 = c2.createCriteria("datoBasico");
		c3.add(Restrictions.eq("codigoDatoBasico", competencia.getDatoBasicoByCodigoEstado().getCodigoDatoBasico() ));	
		List <EquipoCompetencia> lista = c.list(); 
		return lista;
	}
	
	public List<Estadio> listarEstadiosPorFiltro(String dato) {
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Query query = session.createSQLQuery(
				"select *from estadio,dato_basico where estadio.nombre like '" +dato+  "%' and estadio.codigo_parroquia = dato_basico.codigo_dato_basico or estadio.direccion like '" +dato+  "%' and estadio.codigo_parroquia = dato_basico.codigo_dato_basico or dato_basico.nombre like '" +dato+  "%' and estadio.codigo_parroquia = dato_basico.codigo_dato_basico ").addEntity(Estadio.class);
		
		List<Estadio> lista = query.list();
		
		System.out.println(lista.size());
		return lista;
	}

}
