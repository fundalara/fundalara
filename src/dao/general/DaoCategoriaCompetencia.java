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
<<<<<<< HEAD
		//Session session = getSession();
=======
<<<<<<< HEAD
		//Session session = getSession();
=======
<<<<<<< HEAD
		//Session session = getSession();
=======
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(CategoriaCompetencia.class);
		c.add(Restrictions.eq("categoria", cat));
		CategoriaCompetencia cc = (CategoriaCompetencia) c.list().get(0);
		return cc.getDuracionHora();
	}

}
