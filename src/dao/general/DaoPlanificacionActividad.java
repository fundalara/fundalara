package dao.general;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import modelo.Actividad;
import modelo.PlanificacionActividad;
import dao.generico.GenericDao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class DaoPlanificacionActividad extends GenericDao {


	
	public List<PlanificacionActividad> listarVigente(Class<Actividad> class1) {
		
		Date fecha = new Date();	
		List<Actividad> a = super.listar(class1);
		List<PlanificacionActividad> b = new ArrayList<PlanificacionActividad>();
		for(int i=0; i < a.size(); i++){
			if(a.get(i).getFechaInicio().compareTo(fecha) > 0 ){
				b.add(a.get(i).getPlanificacionActividad());
			}
		}
		
		return b;

	}
	
	public List<PlanificacionActividad> listarPlantilla(){
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(PlanificacionActividad.class);
		c.add(Restrictions.eq("actividadPlantilla", true));
		c.add(Restrictions.eq("estatus", "A"));
		List<PlanificacionActividad> lista = c.list();
//		if(session.isOpen())
//			session.close();
		return lista;
	}
	
	public List<PlanificacionActividad> listarMantenimientos(){
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(PlanificacionActividad.class);
		//Se filtran los mantenimientos que no son plantillas
		c.add(Restrictions.eq("actividadPlantilla", false));
		//Se filtran las actividades de mantenimiento
		c.add(Restrictions.eq("datoBasico.codigoDatoBasico", 500));
		c.add(Restrictions.eq("estatus", "A"));
		List<PlanificacionActividad> lista = c.list();
		return lista;
	}
	
	public List<PlanificacionActividad> listarComplementarias(){
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(PlanificacionActividad.class);
//		c.add(Restrictions.eq("actividadPlantilla", false));
		//Se filtran las actividades complementarias
		c.add(Restrictions.eq("datoBasico.codigoDatoBasico", 501));
		c.add(Restrictions.eq("estatus", "A"));
		List<PlanificacionActividad> lista = c.list();
		return lista;
	}

}
