package dao.general;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import modelo.ActividadCalendario;
import modelo.DatoBasico;
import modelo.Sesion;
import dao.generico.GenericDao;

public class DaoActividadCalendario extends GenericDao {
	public ActividadCalendario buscarporCodigo(Integer i){		
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria where =  getSession().createCriteria(ActividadCalendario.class);
		where.add(Restrictions.eq("codigoActividadCalendario", i));
		return (ActividadCalendario)where.uniqueResult();
	}
	public List<ActividadCalendario> listarPorTipoActividad(DatoBasico tipoActividad){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria where =  getSession().createCriteria(ActividadCalendario.class);
		where.add(Restrictions.eq("datoBasico", tipoActividad));
		where.addOrder(Order.asc("fechaInicio"));
		where.addOrder(Order.asc("horaInicio"));
		return where.list();
	}
	public ActividadCalendario buscarSesionFecha(Date fecha, Sesion sesion){		
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria where =  getSession().createCriteria(ActividadCalendario.class);
		where.add(Restrictions.eq("fechaInicio", fecha));
		where.add(Restrictions.eq("sesion", sesion));
		return (ActividadCalendario)where.uniqueResult();
	}
	public List<ActividadCalendario> buscarSesionRangoFecha(Date fecha1, Date fecha2, Sesion sesion){		
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria where =  getSession().createCriteria(ActividadCalendario.class);
		where.add(Restrictions.between("fechaInicio", fecha1, fecha2));
		where.add(Restrictions.eq("sesion", sesion));
		where.add(Restrictions.eq("estatus", 'P'));
		return where.list();
	}
	
	public List<ActividadCalendario> listarDinamico(Date fecha1, Date fecha2, Character estatus){		
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria where =  getSession().createCriteria(ActividadCalendario.class);
		where.add(Restrictions.between("fechaInicio", fecha1, fecha2));
		where.add(Restrictions.eq("estatus", estatus));
		return where.list();
	}

}
