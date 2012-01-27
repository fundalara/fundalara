package dao.general;

import java.util.Date;
import java.util.List;

import modelo.Categoria;
import modelo.CategoriaCompetencia;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoCategoriaCompetencia extends GenericDao {
	
	
	public List listarCategoriaPorCompetencia(Class o, int codigo){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		List lista = session.createCriteria(o).add(Restrictions.eq("competencia.codigoCompetencia", codigo)).list();
		return lista;
	}
	
	public int getDuraccionCategoria(Categoria cat) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(CategoriaCompetencia.class);
		c.add(Restrictions.eq("categoria", cat));
		CategoriaCompetencia cc = (CategoriaCompetencia) c.list().get(0);
		return cc.getDuracionInning();
	}
	
	public Date getDuraccionCategoriaHoras(Categoria cat) {
		//Session session = getSession();
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(CategoriaCompetencia.class);
		c.add(Restrictions.eq("categoria", cat));
		CategoriaCompetencia cc = (CategoriaCompetencia) c.list().get(0);
		return cc.getDuracionHora();
	}

}
