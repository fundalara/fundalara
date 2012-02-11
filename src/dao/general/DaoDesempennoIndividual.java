package dao.general;



import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.DesempennoIndividual;
import modelo.IndicadorCategoriaCompetencia;
import modelo.LineUp;
import dao.generico.GenericDao;

public class DaoDesempennoIndividual extends GenericDao {
      
	   public DesempennoIndividual obtenerDesempennoPorIndicador(IndicadorCategoriaCompetencia icc,LineUp lineUp){
            Session session = getSession();
            Transaction tx = session.beginTransaction();
            Criteria c = session.createCriteria(DesempennoIndividual.class);
            c.add(Restrictions.eq("indicadorCategoriaCompetencia", icc));
            c.add(Restrictions.eq("lineUp", lineUp));           
		    return (DesempennoIndividual) c.uniqueResult();
	   }
	   
	   public List<DesempennoIndividual> obtenerDesempennoJugador(LineUp lineUp){
		   Session session = getSession();
           Transaction tx = session.beginTransaction();
           Criteria c = session.createCriteria(DesempennoIndividual.class);
           c.add(Restrictions.eq("lineUp", lineUp));           
		   return c.list();
		   
	   }
}
