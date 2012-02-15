package dao.general;

import java.util.List;

import modelo.Categoria;
import modelo.Competencia;
import modelo.FaseCompetencia;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoFaseCompetencia extends GenericDao {

	public FaseCompetencia EquiposRegistrados(Competencia competencia) {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(FaseCompetencia.class);
		c.add(Restrictions.eq("numeroFase", 1));
		c.add(Restrictions.eq("competencia", competencia));
		System.out.println(competencia.getCodigoCompetencia());
		return (FaseCompetencia) c.list().get(0);

	}

	public List<FaseCompetencia> listarFaseCompetencia(Competencia cp) {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(FaseCompetencia.class);
		c.add(Restrictions.eq("estatus", 'A'));
		c.add(Restrictions.eq("competencia", cp));

		return c.list();
	}

	public List<FaseCompetencia> listarPorCompetencia(int dato) {
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Query query = session.createSQLQuery(
				"select * from fase_competencia where codigo_competencia = '"
						+ dato + "' and estatus='A' order by numero_fase  ")
				.addEntity(FaseCompetencia.class);
		List<FaseCompetencia> lista = query.list();
		return lista;
	}

	public FaseCompetencia buscarFaseSiguiente(FaseCompetencia faseCompetencia) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(FaseCompetencia.class);
		c.add(Restrictions.eq("competencia", faseCompetencia.getCompetencia()));
		c.add(Restrictions.eq("numeroFase", faseCompetencia.getNumeroFase() + 1));

		return (FaseCompetencia) c.list().get(0);

	}

	public FaseCompetencia buscarFaseCompetencia(Competencia c, int numero) {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(FaseCompetencia.class);
		cr.add(Restrictions.eq("competencia", c));
		cr.add(Restrictions.eq("numeroFase", numero));
		return (FaseCompetencia) cr.uniqueResult();
	}
}
