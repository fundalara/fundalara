package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.DatoBasico;
import modelo.Material;
import dao.generico.GenericDao;
import dao.generico.SessionManager;

public class DaoMaterial extends GenericDao {
	
//	public List<Material> listarMateriales() {
//		
//		List<Material> listaMateriales = getSession().createQuery("FROM Material WHERE estatus = 'A'").list();				
//		getSession().flush();
//		getSession().close();
//		return listaMateriales;
//	}	
	
//	public int contarCodigos(Object o) {
//		Session session = SessionManager.getSession();
//		Transaction tx =  session.beginTransaction();
//	
//		int cantidad = session.createCriteria(o.getClass()).list().size();
//		getSession().close();
//		return cantidad;
//	}
	
	public List<Material> listarMateriales(){
		Session session = getSession(); 
		if(!session.isOpen())
			session = session.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Material.class);
		c.add(Restrictions.eq("estatus", "A"));
		List<Material> lista = c.list();
//		if(session.isOpen())
//			session.close();
		return lista;
	}
	
//	public Material buscarPorCodigo(int codigo){
//		Session session = getSession();
//		Transaction tx =  session.beginTransaction();		
//		Criteria c = session.createCriteria(Material.class);
//		c.add(Restrictions.eq("estatus", "A"));
//		c.add(Restrictions.eq("codigoMaterial", codigo));
//		Material material = (Material)c.list().get(0);
//		material.setDatoBasicoByCodigoTipoMaterial(material.getDatoBasicoByCodigoTipoMaterial());
//		material.setDatoBasicoByCodigoUnidadMedida(material.getDatoBasicoByCodigoUnidadMedida());
//		if(session.isOpen())
//			session.close();
//		return material;
//	}
	

}
