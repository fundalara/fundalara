package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.ActividadCalendario;
import modelo.DatoBasico;

import dao.generico.GenericDao;

public class DaoActividadCalendario extends GenericDao {

	public List<ActividadCalendario> listarPorTipoActividad(DatoBasico tipoActividad){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria where =  getSession().createCriteria(ActividadCalendario.class);
		where.add(Restrictions.eq("datoBasico", tipoActividad));
		return where.list();
	}
}
