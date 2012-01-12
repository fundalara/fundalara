package dao.general;
import java.util.List;
import modelo.Categoria;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import dao.generico.GenericDao;

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


}
