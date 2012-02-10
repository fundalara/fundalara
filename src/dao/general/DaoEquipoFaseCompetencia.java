package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.EquipoFaseCompetencia;
import modelo.FaseCompetencia;

import dao.generico.GenericDao;

public class DaoEquipoFaseCompetencia extends GenericDao {
	
	public List<EquipoFaseCompetencia> buscarEquipoFase (FaseCompetencia fase) {
		Session session = this.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria cc = session.createCriteria(EquipoFaseCompetencia.class);
		cc.add(Restrictions.eq("faseCompetencia", fase));
		cc.add(Restrictions.eq("estatus", 'A'));
		List<EquipoFaseCompetencia> lista = cc.list();
		return lista;
	}
	
	public List<EquipoFaseCompetencia> listarActivos(){		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(EquipoFaseCompetencia.class);
		List lista = c.add(Restrictions.eq("estatus",'A')).list();
		return lista;
	}

}
