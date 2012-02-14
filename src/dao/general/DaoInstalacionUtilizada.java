package dao.general;

import java.util.Date;
import java.util.List;

import modelo.DatoBasico;
import modelo.Instalacion;
import modelo.InstalacionUtilizada;
import modelo.Sesion;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoInstalacionUtilizada extends GenericDao {
	
	public List<InstalacionUtilizada> listarInstalacionDisponible(Date fechaInicio,Date fechaFin,Date horaInicio,Date horaFin){
		
		Session session = getSession(); 
		if(!session.isOpen())
			session = session.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(InstalacionUtilizada.class);
		c.add(Restrictions.ne("fechaInicio", fechaInicio));
		c.add(Restrictions.ne("fechaInicio", fechaInicio));
		c.add(Restrictions.ne("fechaFin", fechaFin));
		c.add(Restrictions.ne("horaInicio", horaInicio));
		c.add(Restrictions.ne("horaFin", horaFin));
		c.add(Restrictions.eq("estatus", "A"));
		List<InstalacionUtilizada> lista = c.list();
		return lista;
	}
	
public List<InstalacionUtilizada> listarInstalacionOcupada(Date fechaInicio,Date fechaFin,Date horaInicio,Date horaFin){
		
		Session session = getSession(); 
		if(!session.isOpen())
			session = session.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(InstalacionUtilizada.class);
		System.out.println("hola estoy en el servicio de instalacionUtilizada");
		c.add(Restrictions.between("fechaInicio", fechaInicio,fechaFin));
		c.add(Restrictions.between("fechaFin", fechaInicio,fechaFin));
		c.add(Restrictions.between("horaInicio", horaInicio,horaFin));
		c.add(Restrictions.between("horaFin", horaInicio,horaFin));
		c.add(Restrictions.eq("estatus", "A"));
		List<InstalacionUtilizada> lista = c.list();
		return lista;
	}

public List<InstalacionUtilizada> buscarporSesion(Sesion se) {
	// TODO Auto-generated method stub
	Session session = getSession();
	Transaction tx =  session.beginTransaction();
	Criteria c = session.createCriteria(InstalacionUtilizada.class);
	c.add(Restrictions.eq("sesion", se));
	c.add(Restrictions.eq("estatus", "A"));
	return c.list();
}

public InstalacionUtilizada buscarporCodigo(Integer i){		
	Session session = getSession();
	Transaction tx =  session.beginTransaction();
	Criteria where =  getSession().createCriteria(InstalacionUtilizada.class);
	where.add(Restrictions.eq("codigoInstalacionUtilizada", i));
	return (InstalacionUtilizada)where.uniqueResult();
}

public InstalacionUtilizada buscarPorSesionFecha(Date fec, Sesion se){
	Session session = getSession();
	Transaction tx =  session.beginTransaction();
	Criteria c = session.createCriteria(InstalacionUtilizada.class);
	c.add(Restrictions.eq("fechaInicio", fec));
	c.add(Restrictions.eq("sesion", se));
	c.add(Restrictions.eq("estatus", "A"));
	return (InstalacionUtilizada) c.uniqueResult();
}

public InstalacionUtilizada buscarporInstalacionFecha(Instalacion it,Date fc) {
	// TODO Auto-generated method stub
	Session session = getSession();
	Transaction tx =  session.beginTransaction();
	Criteria c = session.createCriteria(InstalacionUtilizada.class);
	c.add(Restrictions.eq("fechaInicio", fc));
	c.add(Restrictions.eq("instalacion", it));
	c.add(Restrictions.eq("estatus", "A"));
	return (InstalacionUtilizada) c.uniqueResult();
}
	
}
