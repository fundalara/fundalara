package dao.general;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import modelo.DatoBasico;
import modelo.Persona;
import modelo.TallaPorIndumentaria;
import dao.generico.GenericDao;

public class DaoTallaPorIndumentaria extends GenericDao {

	public TallaPorIndumentaria buscarPorTalla(Integer i , Integer e){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(TallaPorIndumentaria.class);
		c.add(Restrictions.eq("datoBasicoByCodigoTipoUniforme.codigoDatoBasico", i)).add(Restrictions.eq("datoBasicoByCodigoTalla.codigoDatoBasico", e))
				.add(Restrictions.eq("estatus", 'A'));
		if (c.list().isEmpty())
			return null;
		else
			return (TallaPorIndumentaria) c.list().get(0);
	}
	
	public List<TallaPorIndumentaria> listarActivosOrdenados() {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(TallaPorIndumentaria.class);
		c.addOrder(Order.asc("datoBasicoByCodigoTipoUniforme"));
		c.addOrder(Order.asc("datoBasicoByCodigoTalla"));
		c.add(Restrictions.eq("estatus", 'A'));

		return (List<TallaPorIndumentaria>) c.list();
	}
	
	/**
	 * Obtiene una talla por indumentaria dado un datoBasico
	 * 
	 * @param datoBasico
	 *            datoBasico que representa un talla
	 * @return TallaPorIndumentaria de uniforme de entrenamiento
	 * 
	 */
	public TallaPorIndumentaria buscarPorDatoBasico(DatoBasico datoBasico) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(TallaPorIndumentaria.class);
		c.add(Restrictions.eq("datoBasicoByCodigoTalla.codigoDatoBasico", datoBasico.getCodigoDatoBasico()));
		c.add(Restrictions.eq("datoBasicoByCodigoTipoUniforme.codigoDatoBasico", 52));
		c.add(Restrictions.eq("estatus", "A"));
		TallaPorIndumentaria result = (TallaPorIndumentaria) c.uniqueResult();
		tx.commit();
		return result;
	}
	
}