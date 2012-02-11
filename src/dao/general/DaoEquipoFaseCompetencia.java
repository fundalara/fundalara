package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.Categoria;
import modelo.EquipoFaseCompetencia;
import modelo.FaseCompetencia;

import dao.generico.GenericDao;

public class DaoEquipoFaseCompetencia extends GenericDao {
	
	public List<EquipoFaseCompetencia> buscarEquipoPorFaseYCategoria (FaseCompetencia fase,Categoria categoria) {
		Session session = this.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(EquipoFaseCompetencia.class);		
		c.add(Restrictions.eq("faseCompetencia", fase));
		c.add(Restrictions.eq("estatus", 'A'));
		Criteria c2 = c.createCriteria("equipoCompetencia");
		Criteria c3 = c2.createCriteria("equipo");
		c3.add(Restrictions.eq("categoria",categoria));
		List<EquipoFaseCompetencia> lista = c.list();
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
