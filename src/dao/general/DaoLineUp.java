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
<<<<<<< HEAD
import modelo.RosterCompetencia;
=======
<<<<<<< HEAD
import modelo.RosterCompetencia;
=======
<<<<<<< HEAD
import modelo.RosterCompetencia;
=======
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514

import dao.generico.GenericDao;

public class DaoLineUp extends GenericDao {
<<<<<<< HEAD

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

<<<<<<< HEAD
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

<<<<<<< HEAD
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

=======
=======
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
		return c21.list();
       
	}
	
	
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
     
	 public List<LineUp> listarPlanificados(Juego j,Equipo e){
		  
		     Session session = getSession();
		     Transaction tx = session.beginTransaction();
		     
		     Criteria c = session.createCriteria(DatoBasico.class);
		     c.add(Restrictions.eq("nombre","BORRADOR"));
		     DatoBasico db = (DatoBasico) c.list().get(0);
			     
		     c = session.createCriteria(LineUp.class);
		     c.add(Restrictions.eq("datoBasicoByCodigoEstadoLineUp", db));
		     c.add(Restrictions.eq("juego", j));
		     c.add(Restrictions.eq("rosterCompetencia.roster.equipo.codigoEquipo",e.getCodigoEquipo()));
		     return c.list();
	 }
	 
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
}
