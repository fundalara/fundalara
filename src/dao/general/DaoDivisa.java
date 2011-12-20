package dao.general;

import java.util.List;

import modelo.Divisa;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;


public class DaoDivisa extends GenericDao {
	
	//Metodos personalizados
	/*public List<Divisa> listarActivos(){
		//Permite buscar todas las divisas con estatus = 'A'
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(Divisa.class);
		List lista = c.add(Restrictions.eq("estatus",'A')).list();
		return lista;
	}*/

}
