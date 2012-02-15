package dao.general;

import java.util.List;

import modelo.Divisa;
import modelo.RosterCompetencia;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.zkoss.zhtml.Li;

import dao.generico.GenericDao;

public class DaoRosterCompetencia extends GenericDao {
	
	public List<RosterCompetencia> listarActivos(){
		//Permite buscar todas las divisas con estatus = 'A'
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(RosterCompetencia.class);
		List lista = c.add(Restrictions.eq("estatus",'A')).list();
		return lista;
	}
	
	public List<RosterCompetencia> listarRexistentes(int codcomp,int codequipo){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(RosterCompetencia.class);
		c.add(Restrictions.eq("competencia.codigoCompetencia", codcomp));
		c.add(Restrictions.eq("equipo.codigoEquipo", codequipo));
		List lista = c.list();
		return lista;
		
		
		}
	
	public List<RosterCompetencia> listarJexistentes(int codcomp){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(RosterCompetencia.class);
		c.add(Restrictions.eq("competencia.codigoCompetencia", codcomp));
		
		List lista = c.list();
		return lista;
		
		
		}
}

