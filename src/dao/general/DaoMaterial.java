package dao.general;

import java.util.List;

import modelo.DatoBasico;
import modelo.Material;
import modelo.NotaEntrega;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoMaterial extends GenericDao {	
	
	public List<Material> listarMateriales(){
		Session session = getSession(); 
		if(!session.isOpen())
			session = session.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Material.class);
		c.add(Restrictions.eq("estatus", "A"));
		List<Material> lista = c.list();		
		return lista;
	}
	
	public List<Material> listarMaterialesPorTipo(DatoBasico tipo){
		Session session = getSession(); 
		if(!session.isOpen())
			session = session.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Material.class);
		c.add(Restrictions.eq("estatus", "A"));
		c.add(Restrictions.eq("datoBasicoByCodigoTipoMaterial.codigoDatoBasico",tipo.getCodigoDatoBasico()));
		List<Material> lista = c.list();
		System.out.println(lista.size());
		return lista;
	}

	public List<Material> buscarPorRelacion(DatoBasico material){		
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(Material.class);
		c.add(Restrictions.eq("datoBasicoByCodigoTipoMaterial",material));
		c.add(Restrictions.eq("estatus", "A"));
		List<Material> lista = c.list();
		return lista;
	}
	
	public int devolverUltimo() {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Query c = session
				.createQuery("SELECT max(codigoNotaEntrega) FROM NotaEntrega");
		return (Integer) c.uniqueResult();
	}

	public NotaEntrega buscarPorCodigo(Integer cod) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(NotaEntrega.class);
		c.add(Restrictions.eq("codigoNotaEntrega", cod));
		List<NotaEntrega> lista = c.list();
		return lista.get(0);
	}
	
}
