package dao.general;

import java.util.Date;
import java.util.List;

import modelo.DatoBasico;
import modelo.Instalacion;
import modelo.InstalacionUtilizada;

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
		c.add(Restrictions.ne("fechaFin", fechaInicio));
		c.add(Restrictions.ne("horaInicio", fechaInicio));
		c.add(Restrictions.ne("horaFin", fechaInicio));
		c.add(Restrictions.eq("estatus", "A"));
		List<InstalacionUtilizada> lista = c.list();
		return lista;
	}
}
