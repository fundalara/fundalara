package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.ActividadEjecutada;
import modelo.SesionEjecutada;
import dao.generico.GenericDao;
import dao.generico.SessionManager;

public class DaoActividadesEjecutadas extends GenericDao {

	public List<ActividadEjecutada>  buscarPorSesionEjecutada(SesionEjecutada sesionEjecutada) {
		// TODO Auto-generated method stub
		Session session = SessionManager.getSession();
		Transaction tx =  session.beginTransaction();
		Criteria criteria=session.createCriteria(ActividadEjecutada.class);
		criteria.add(Restrictions.eq("sesionEjecutada", sesionEjecutada));
		criteria.add(Restrictions.eq("estatus", 'A'));
		List lista = criteria.list();
		return lista;
	}

}
