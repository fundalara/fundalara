package dao.general;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
}
