package dao.general;

import modelo.TipoDato;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoTipoDato extends GenericDao {
	
	public TipoDato buscarPorTipo(String tipo) {
		// TODO Auto-generated method stub
		Criteria c = getSession().createCriteria(TipoDato.class);
		c.add(Restrictions.eq("nombre", tipo));
		return (TipoDato) c.list().get(0);
	}
}
