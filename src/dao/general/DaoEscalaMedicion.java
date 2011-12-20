package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import modelo.EscalaMedicion;
import dao.generico.GenericDao;

public class DaoEscalaMedicion extends GenericDao {

	public EscalaMedicion buscarCodigo(String codigo){
		Criteria criteria = getSession().createCriteria(EscalaMedicion.class);
		criteria.add(Restrictions.eq("codigoEscala", codigo));
		return (EscalaMedicion) criteria.list().get(0);
	}
}
