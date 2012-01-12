package dao.general;

import java.util.List;

import modelo.EscalaMedicion;
import modelo.ValorEscala;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoValorEscala extends GenericDao {
	
	public List<ValorEscala> buscarValores(EscalaMedicion escala){
		Criteria criteria = getSession().createCriteria(ValorEscala.class);
		criteria.add(Restrictions.eq("escalaMedicion", escala));
		return (List <ValorEscala>) criteria.list();
	}
	
	
}
