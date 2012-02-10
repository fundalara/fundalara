package dao.general;

import java.util.List;

import modelo.Categoria;
import modelo.Constante;
import modelo.ConstanteCategoria;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoConstanteCategoria extends dao.generico.GenericDao {
	
public List<ConstanteCategoria> listarConstantesPorCategoria( Constante c){
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cc = session.createCriteria(Constante.class);
		cc = session.createCriteria(ConstanteCategoria.class);
		cc.add(Restrictions.eq("constante",c));
		cc.add(Restrictions.eq("estatus",'A'));
		
		List<ConstanteCategoria> lista = cc.list();
		return lista;
		
	}

}
