package dao.general;

import java.util.List;

import modelo.DatoBasico;
import modelo.IngresoInscripcion;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoIngresoInscripcion extends GenericDao {
	
	public List<IngresoInscripcion> listarTipoInscrpcion(DatoBasico d) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(IngresoInscripcion.class);
		c.add(Restrictions.eq("datoBasico", d));
		// c.createCriteria("datoBasico").add(Restrictions.eq("nombre",
		// tipoInscripcion));
		c.add(Restrictions.eq("estatus", 'A'));

		return (List<IngresoInscripcion>) c.list();
	}

	
	

}


