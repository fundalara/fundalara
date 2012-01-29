package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.ComisionActividadPlanificada;
import modelo.PlanificacionActividad;
import dao.generico.GenericDao;

public class DaoComisionActividadPlanificada extends GenericDao {

	public List<ComisionActividadPlanificada> listarPorPlanificacion(
			PlanificacionActividad a) {
		
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(ComisionActividadPlanificada.class);
		c.add(Restrictions.eq("planificacionActividad.codigoPlanificacionActividad", a.getCodigoPlanificacionActividad()));
		List<ComisionActividadPlanificada> lista = c.list();
		return lista;
	}

}
