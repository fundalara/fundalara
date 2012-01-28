package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.DatoBasico;
import modelo.Material;

import dao.generico.GenericDao;

/**
 * Clase DAO para acceso/manejo de los materiales  
 * @author Reinaldo L.
 *
 */
public class DaoMaterial extends GenericDao {	
	
	public List<Material> listarPorTipo(DatoBasico tipo) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(Material.class);
		c.add(Restrictions.eq("datoBasicoByCodigoTipoMaterial", tipo));
		c.add(Restrictions.eq("estatus", 'A'));
		return c.list();
	}
	
}
