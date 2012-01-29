package dao.general;

import java.util.ArrayList;
import java.util.List;

import modelo.Actividad;
import modelo.PlanificacionActividad;
import modelo.TareaActividadPlanificada;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;


public class DaoTareaActividadPlanificada extends GenericDao {
	
	public List<TareaActividadPlanificada> listarTareas(PlanificacionActividad planActividad){
		
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(TareaActividadPlanificada.class);
		c.add(Restrictions.eq("planificacionActividad.codigoPlanificacionActividad", planActividad.getCodigoPlanificacionActividad()));
		c.add(Restrictions.eq("estatus", "A"));
		List<TareaActividadPlanificada> lista = c.list();
		System.out.print(lista.size());
		return lista;
	}
	public void insertar(int codigo){
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Query c = session.createSQLQuery("insert into tarea_actividad_planificada (codigo_personal_actividad_planificada,estatus) values ("+codigo +",'A')");
	}

	public List<TareaActividadPlanificada> listarTareas(Actividad a){
		
		List<TareaActividadPlanificada> c = new ArrayList<TareaActividadPlanificada>();
		List<TareaActividadPlanificada> b = this.listar(TareaActividadPlanificada.class);
		
		for(int i = 0; i < b.size(); i++){
			if(b.get(i).getPlanificacionActividad().getCodigoPlanificacionActividad() == a.getPlanificacionActividad().getCodigoPlanificacionActividad()){
				c.add(b.get(i));
			}
		}
		return c;
		
	}
}
