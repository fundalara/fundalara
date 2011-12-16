package dao.competencia;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.general.GenericDAO;
import dao.general.GenericDAO;
import dao.general.SessionManager;

public class DaoDivisa extends GenericDAO {
	
	public List listarActivos(Class o){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		List lista = session.createCriteria(o).add(Restrictions.eq("estatus", "A")).list();
		return lista;
	}

}
