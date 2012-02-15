package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.Medico;
import dao.generico.GenericDao;

public class DaoMedico extends GenericDao {
	
	public Medico buscar (String id){
		Session session = this.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
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
	
	public List<Medico> cargarLista(String filtro1,String filtro2,String filtro3,String filtro4){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		
		Criteria c = session.createCriteria(Medico.class)
				.add(Restrictions.like("numeroColegio", filtro1+"%"))
				.add(Restrictions.like("matricula", filtro2+"%"))
				.add(Restrictions.like("nombre", filtro3+"%"))
				.add(Restrictions.like("apellido", filtro4+"%"));
		List<Medico> lista= c.list();
		return lista;
	}
	
}