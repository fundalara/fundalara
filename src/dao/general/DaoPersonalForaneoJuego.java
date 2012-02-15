package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;





import modelo.Juego;
import modelo.PersonalForaneo;
import modelo.PersonalForaneoJuego;
import dao.generico.GenericDao;

public class DaoPersonalForaneoJuego extends GenericDao {
	
	public List<PersonalForaneoJuego> listarUmpireJuego (Juego j){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		
		Criteria c = session.createCriteria(PersonalForaneoJuego.class);
		c.add(Restrictions.eq("juego",j));
		System.out.println(j.getCodigoJuego());
		
		return c.list();
		
	}
	


}
