package dao.general;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.Equipo;
import modelo.Sesion;
import modelo.SesionEjecutada;
import dao.generico.GenericDao;

public class DaoSesionEjecutada extends GenericDao {

	public SesionEjecutada buscarPorFechaHoraEquipo(Equipo equipo, Date fecha,
			Date horaFin, Date horaInicio) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria cri = session.createCriteria(SesionEjecutada.class);
		cri.add(Restrictions.eq("equipo",equipo ));
		cri.add(Restrictions.eq("fecha", fecha));
		cri.add(Restrictions.eq("horaFin", horaFin));
		cri.add(Restrictions.eq("horaInicio", horaInicio));
		cri.add(Restrictions.eq("estatus", 'A'));
	    return (SesionEjecutada) cri.uniqueResult();
	}
	
	public SesionEjecutada buscarSesionFecha(Sesion sesion, Date fecha) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria cri = session.createCriteria(SesionEjecutada.class);
		cri.add(Restrictions.eq("sesion",sesion ));
		cri.add(Restrictions.eq("fecha", fecha));
	    return (SesionEjecutada) cri.uniqueResult();
	}

	public SesionEjecutada buscarPorSesionEquipoFecha(Sesion sesion,Equipo equipo,
			Date fecha) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria cri = session.createCriteria(SesionEjecutada.class);
		cri.add(Restrictions.eq("sesion",sesion ));
		cri.add(Restrictions.eq("fecha", fecha));
		cri.add(Restrictions.eq("equipo", equipo));
	    return (SesionEjecutada) cri.uniqueResult();
	}
	
}
