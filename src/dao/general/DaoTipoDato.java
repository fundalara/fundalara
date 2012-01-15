package dao.general;

import java.util.List;

import modelo.ActividadEntrenamiento;
import modelo.TipoDato;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import comun.TipoDatoBasico;



import dao.generico.GenericDao;

public class DaoTipoDato extends GenericDao {
	
	public TipoDato buscarPorTipo(String tipo) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(TipoDato.class);
		c.add(Restrictions.eq("nombre", tipo));
		return (TipoDato) c.uniqueResult();
	}
	
	public List <TipoDato> buscarTrue(Boolean bool) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(TipoDato.class);
		c.add(Restrictions.eq("tipo", bool));
		return  c.list();
	}
	
	

	/**
	 * Busca el tipo de dato completo segun su tipo(c�digo)
	 * @param tipoDato tipo de dato b�sico
	 * @return Objeto de TipoDato, en caso de no encontrar retorna null
	 */
	public TipoDato buscarPorCodigo(TipoDatoBasico tipoDato) {
		TipoDato tipo;
		Session session = SessionManager.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Query query = session.createSQLQuery(
				"SELECT * FROM tipo_dato WHERE codigo_tipo_dato='"
						+ tipoDato.getCodigo() + "'").addEntity(TipoDato.class);
		List<Object> lista = query.list();
		if (!lista.isEmpty()) {
			tipo = (TipoDato) lista.get(0);
		} else {
			tipo = null;
		}
		tx.commit();
		return tipo;
	}
}
