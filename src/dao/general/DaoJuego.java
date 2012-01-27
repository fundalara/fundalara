package dao.general;
import modelo.DatoBasico;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import modelo.Juego;
import dao.generico.GenericDao;

public class DaoJuego extends GenericDao {

	public int listarJuegosProgramados(DatoBasico datoBasico) {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		
		Criteria q = session.createCriteria(DatoBasico.class);
        q.add(Restrictions.eq("nombre", "SUSPENDIDO"));
        DatoBasico db1 = (DatoBasico) q.list().get(0);	
        
        Criteria d = session.createCriteria(DatoBasico.class);
        d.add(Restrictions.eq("nombre", "CULMINADO"));
        DatoBasico db2 = (DatoBasico) d.list().get(0);
        
		Criteria p = session.createCriteria(Juego.class);
		Criterion cr1 = Restrictions.eq("datoBasico", db1 );
		Criterion cr2 = Restrictions.eq("datoBasico", db2 );
		
		p.add(Restrictions.or(cr1, cr2));
	
		
		return (p.list().size());
    
	}

}
