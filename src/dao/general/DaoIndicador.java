package dao.general;

import java.util.List;

import dao.generico.GenericDao;
import modelo.DatoBasico;
import modelo.EscalaMedicion;
import modelo.Indicador;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoIndicador extends GenericDao {

	public List listarActivos(Class o) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		List lista = session.createCriteria(o)
				.add(Restrictions.eq("estatus", "A")).list();
		return lista;
	}

	public List<Indicador> buscarPorDatoBasico(DatoBasico datoBasico) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = getSession().createCriteria(Indicador.class);
		criteria.add(Restrictions.eq("datoBasicoByCodigoModalidad", datoBasico));
		criteria.add(Restrictions.eq("estatus", 'A'));
		return criteria.list();
	}
}