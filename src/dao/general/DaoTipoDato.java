package dao.general;

import java.util.List;

import modelo.TipoDato;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;



import dao.generico.GenericDao;

public class DaoTipoDato extends GenericDao {
	
	public TipoDato buscarPorTipo(String tipo) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(TipoDato.class);
		c.add(Restrictions.eq("nombre", tipo));
		return (TipoDato) c.list().get(0);
	}
	
	public List <TipoDato> buscarTrue(Boolean bool) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(TipoDato.class);
		c.add(Restrictions.eq("tipo", bool));
		return  c.list();
	}
}
