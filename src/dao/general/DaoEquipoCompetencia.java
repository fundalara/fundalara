package dao.general;

import java.util.List;

import modelo.Competencia;
import modelo.EquipoCompetencia;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoEquipoCompetencia extends GenericDao {
	
	//Metodos personalizados
	public List<EquipoCompetencia> listarActivos(){
		//Permite buscar todas las divisas con estatus = 'A'
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(EquipoCompetencia.class);
		List lista = c.add(Restrictions.eq("estatus",'A')).list();
		return lista;
	}
	

public List<EquipoCompetencia> buscarEquipoporCompetencia(Competencia c) {
		Session session = this.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria cc = session.createCriteria(EquipoCompetencia.class);
		cc.add(Restrictions.eq("competencia", c));
		cc.add(Restrictions.eq("estatus", 'A'));
		List<EquipoCompetencia> lista = cc.list();
		return lista;
	}
	
	
	
	

}
