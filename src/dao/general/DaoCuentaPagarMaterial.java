package dao.general;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;
import modelo.CuentaPagarMaterial;

public class DaoCuentaPagarMaterial extends GenericDao {
	
	public List<CuentaPagarMaterial> listarMaterialesCP(String nf) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(CuentaPagarMaterial.class);
		c.add(Restrictions.eq("cuentaPagar.origen", nf))
				.add(Restrictions.eq("estatus", 'A')).list();
		return (List<CuentaPagarMaterial>) c.list();
	}


}
