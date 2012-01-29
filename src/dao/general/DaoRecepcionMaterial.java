package dao.general;

import java.util.List;

import modelo.RecepcionMaterial;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.generico.GenericDao;

public class DaoRecepcionMaterial extends GenericDao {
	
	public List<RecepcionMaterial> listarMateriales() {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		List<RecepcionMaterial> l = getSession().createCriteria(
				RecepcionMaterial.class).list();
		getSession().flush();
		return l;
	}

}
