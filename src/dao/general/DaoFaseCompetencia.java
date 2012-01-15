package dao.general;


import modelo.Competencia;
import modelo.FaseCompetencia;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoFaseCompetencia extends GenericDao {
	public FaseCompetencia EquiposRegistrados(Competencia competencia) {
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(FaseCompetencia.class);
		c.add(Restrictions.eq("id.numeroFase", 1));
		c.add(Restrictions.eq("competencia", competencia));
		System.out.println(competencia.getCodigoCompetencia());
	   return (FaseCompetencia) c.list().get(0);
	   
	}

}
