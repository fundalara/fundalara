package dao.competencia;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.prueba.GenericDao;

public class DaoCategoriaCompetencia extends GenericDao {
	
	
	public List listarCategoriaPorCompetencia(Class o, String codigo){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		List lista = session.createCriteria(o).add(Restrictions.eq("competencia.codigoCompetencia", codigo)).list();
		return lista;
	}

}
