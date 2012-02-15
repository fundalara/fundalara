package dao.general;

import java.util.List;

import modelo.JugadorForaneo;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoJugadorForaneo extends GenericDao {

	
	
	//Metodos personalizados
		public List<JugadorForaneo> listarActivos(){
			//Permite buscar todas las divisas con estatus = 'A'
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			Criteria c = getSession().createCriteria(JugadorForaneo.class);
			List lista = c.add(Restrictions.eq("estatus",'A')).list();
			return lista;
		}
		
		public List<JugadorForaneo> filtar(String cad){
			
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			Criteria c = session.createCriteria(JugadorForaneo.class);
			c.add(Restrictions.like("nombre", cad));
			return c.list();
			
		}
		
		public List<JugadorForaneo> buscarJugadorForaneo(String ced) {
			Session session = this.getSession();
			org.hibernate.Transaction tx = session.beginTransaction();
			Criteria c = session.createCriteria(JugadorForaneo.class);
					c.add(Restrictions.eq("cedula", ced));
					c.add(Restrictions.eq("estatus", 'A'));
			List<JugadorForaneo> lista = c.list();
			return lista;
		}
		
		public List listarJugadorForaneoPorCategoria(Class o, int codigo){
			Session session = getSession(); 
			Transaction tx =  session.beginTransaction();
			Criteria c = session.createCriteria(JugadorForaneo.class);
				Criteria c2 = c.createCriteria("equipo");
			c2.add(Restrictions.eq("categoria.codigoCategoria",codigo));
			List <JugadorForaneo> lista = c.list(); 
			return lista;
		}
		public List<JugadorForaneo> listarJugadorForaneoPorFiltro(String dato) {
			Session session = getSession();
			org.hibernate.Transaction tx = session.beginTransaction();
			Query query = session.createSQLQuery(
					"select *from jugador_foraneo,equipo where jugador_foraneo.nombre like '" +dato+  "%' and jugador_foraneo.codigo_equipo = equipo.codigo_equipo and jugador_foraneo.estatus='A' or equipo.nombre like '" +dato+  "%' and jugador_foraneo.codigo_equipo = equipo.codigo_equipo and jugador_foraneo.estatus='A'").addEntity(JugadorForaneo.class);
			
			List<JugadorForaneo> lista = query.list();
			
			System.out.println(lista.size());
			return lista;
		}


	
	
}