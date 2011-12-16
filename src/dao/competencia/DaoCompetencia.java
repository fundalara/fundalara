package dao.competencia;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.prueba.GenericDao;

public class DaoCompetencia extends GenericDao {
	
	public List listarActivos(Class o){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		List lista = session.createCriteria(o).add(Restrictions.eq("estadoCompetencia.codigoEstadoCompetencia", "1")).list();
		return lista;
	}

}
