package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.Competencia;
import modelo.DatoBasico;
import modelo.Equipo;
import modelo.Juego;
import modelo.LineUp;
import modelo.RosterCompetencia;

import dao.generico.GenericDao;

public class DaoLineUp extends GenericDao {

	public List<LineUp> listarPlanificados(Juego j, Equipo e) {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		
		Criteria c1 = session.createCriteria(DatoBasico.class);
		c1.add(Restrictions.eq("nombre", "BORRADOR"));
		DatoBasico db = (DatoBasico) c1.list().get(0);
		
		Criteria c2 = session.createCriteria(LineUp.class);
		c2.add(Restrictions.eq("datoBasicoByCodigoEstadoLineUp", db));
		c2.add(Restrictions.eq("juego",j));
		
		Criteria c21 = c2.createCriteria("rosterCompetencia");
		c21.add(Restrictions.eq("equipo",e));
			
		return c2.list();
       
	}
	
	public List<LineUp> listarDefinivos(Juego j, Equipo e) {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		
		Criteria c1 = session.createCriteria(DatoBasico.class);
		c1.add(Restrictions.eq("nombre", "DEFINITIVO"));
		DatoBasico db = (DatoBasico) c1.list().get(0);
		
		Criteria c2 = session.createCriteria(LineUp.class);
		c2.add(Restrictions.eq("datoBasicoByCodigoEstadoLineUp", db));
		c2.add(Restrictions.eq("juego",j));
		
		Criteria c21 = c2.createCriteria("rosterCompetencia");
		c21.add(Restrictions.eq("equipo",e));

		return c21.list();
       
	}
	
	public List<LineUp> listarDefinitivosReserva(Juego j, Equipo e) {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		
		Criteria c1 = session.createCriteria(DatoBasico.class);
		c1.add(Restrictions.eq("nombre", "DEFINITIVO"));
		DatoBasico db = (DatoBasico) c1.list().get(0);
		
		c1 = session.createCriteria(DatoBasico.class);
		c1.add(Restrictions.eq("nombre","R"));
		DatoBasico db2 = (DatoBasico) c1.list().get(0);
		
		Criteria c2 = session.createCriteria(LineUp.class);
		c2.add(Restrictions.eq("datoBasicoByCodigoEstadoLineUp", db));
		c2.add(Restrictions.eq("juego",j));
		c2.add(Restrictions.eq("datoBasicoByCodigoPosicion",db2));
		
		Criteria c21 = c2.createCriteria("rosterCompetencia");
		c21.add(Restrictions.eq("equipo",e));

		return c21.list();
       
	}
	
	
}
