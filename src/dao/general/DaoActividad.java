package dao.general;

import java.util.ArrayList;
import java.util.List;

import modelo.Actividad;
import modelo.PlanificacionActividad;
import modelo.TareaActividad;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoActividad extends GenericDao {
	
	public Actividad buscarActividad(PlanificacionActividad a) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(Actividad.class);
		List lista = c.add(Restrictions.eq("estatus", 'A'))
				.add(Restrictions.eq("planificacionActividad", a)).list();

		try {
			return (Actividad) lista.get(0);

		} catch (Exception e) {
			return null;
		}
	}

	public Actividad BuscarActividad(PlanificacionActividad a,
			Class<Actividad> class1) {

		Actividad h = null;
		List<Actividad> c = super.listar(class1);

		for (int i = 0; i < c.size(); i++) {
			if (c.get(i).getPlanificacionActividad()
					.getCodigoPlanificacionActividad() == a
					.getCodigoPlanificacionActividad()) {

				h = c.get(i);
			}
		}
		return h;
	}

	public List<TareaActividad> listar(Actividad actividad) {

		List<TareaActividad> a = this.listar(TareaActividad.class);
		List<TareaActividad> b = new ArrayList<TareaActividad>();

		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).getActividad().getCodigoActividad() == actividad
					.getCodigoActividad()) {
				b.add(a.get(i));
			}
		}
		return b;
	}
}
