package dao.general;

import java.util.List;

import modelo.IngresoInscripcion;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoIngresoInscripcion extends GenericDao {
	
	public List<IngresoInscripcion> listarTipoInscrpcion(String tipoInscripcion) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(IngresoInscripcion.class);
		c.createCriteria("datoBasico").add(Restrictions.eq("nombre", tipoInscripcion));
		c.add(Restrictions.eq("estatus", 'A'));
		if (c.list().size()==0){
			return null;
		} else {
			return  c.list();
		}
	}
	
}


