package dao.general;

import dao.generico.GenericDao;

import java.util.List;

import modelo.Estadio;
import modelo.Liga;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class DaoLiga extends GenericDao{
	public List<Liga> listarActivos(){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Liga.class);
		c.add(Restrictions.eq("estatus",'A'));
		return c.list();
	}

	
	
	public List<Liga> listarLigasPorFiltro(String dato) {
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		String sentencia;
		sentencia= "select * from liga where liga.nombre like '" +dato+  "%' or liga.localidad like '" +dato+  "%' and estatus='A' ";
		System.out.println(sentencia);
		
		Query query = session.createSQLQuery(sentencia).addEntity(Liga.class);
		
		List<Liga> lista = query.list();
		
		
		return lista;
	}
}
