package dao.general;

import java.util.List;

import modelo.Representante;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoHospedaje extends GenericDao {
	
	public Representante buscar (String id){
		Criteria c = getSession().createCriteria(Representante.class);
		c.add(Restrictions.eq("Representante", id));
		List list = c.list();
		if (list.size() > 0){
			return (Representante) list.get(0);
		}
		else {
			return null;
		}
	}
}