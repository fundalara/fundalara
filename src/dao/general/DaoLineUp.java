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

import dao.generico.GenericDao;

public class DaoLineUp extends GenericDao {
     
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
	 
}
