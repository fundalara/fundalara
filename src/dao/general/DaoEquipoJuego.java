package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.Competencia;
import modelo.EquipoCompetencia;
import modelo.EquipoJuego;

import dao.generico.GenericDao;
import dao.generico.SessionManager;

public class DaoEquipoJuego extends GenericDao {
	
	public List<EquipoJuego> buscarJuegoporCompetencia(Competencia c,EquipoCompetencia ec){
		
		Session session = SessionManager.getSession();
		Transaction tx = session.beginTransaction();   
		
		Criteria cc = session.createCriteria(EquipoJuego.class);	
		cc.add(Restrictions.eq("equipoCompetencia", ec));
		
		Criteria cc2 = session.createCriteria("juego");
		cc2.add(Restrictions.eq("competencia", c));
		return cc.list();
			
	}
}
