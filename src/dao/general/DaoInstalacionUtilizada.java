package dao.general;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.Instalacion;
import modelo.InstalacionUtilizada;
import modelo.Sesion;
import dao.generico.GenericDao;
public class DaoInstalacionUtilizada extends GenericDao {

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


