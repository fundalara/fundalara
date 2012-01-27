package dao.general;

import java.util.List;

import modelo.JugadorForaneo;

import org.hibernate.Criteria;
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

	
	
}