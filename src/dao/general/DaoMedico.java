package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import modelo.Medico;
import dao.generico.GenericDao;

public class DaoMedico extends GenericDao {
	
	public Medico buscar (String id){
		Criteria c = getSession().createCriteria(Medico.class);
		c.add(Restrictions.eq("numeroColegio", id));
		List list = c.list();
		if (list.size() > 0){
			return (Medico) list.get(0);
		}
		else {
			return null;
		}
	}
	
	public boolean verificareli(Medico medico){
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Query query = session
				.createSQLQuery("select * from medico,dato_medico where dato_medico.numero_colegio=medico.numero_colegio and dato_medico.numero_colegio="
								+ medico.getNumeroColegio()
								+ "").addEntity(Medico.class);
		List<Object> lista = query.list();
		if (lista.size() > 0){
			return false;
		}
		else {
			return true;
		}
	}
}
