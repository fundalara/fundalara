package dao.general;

import java.util.List;

import modelo.Persona;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoPersona extends GenericDao {
	public Persona buscarPorTipoPersona(String s, Integer i) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(Persona.class);
		c.add(Restrictions.eq("cedulaRif", s))
				.add(Restrictions.eq("estatus", 'A')).add(Restrictions.eq("datoBasicoByCodigoTipoPersona.codigoDatoBasico",i));
		if (c.list().isEmpty())
			return null;
		else
			return (Persona) c.list().get(0);
	}
	public Persona buscarPorCedulaRif(String s) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(Persona.class);
		c.add(Restrictions.eq("cedulaRif", s))
				.add(Restrictions.eq("estatus", 'A'));
		if (c.list().isEmpty())
			return null;
		else
			return (Persona) c.list().get(0);
	}
	
	public List<Persona> listarPorTipo(String tipoPersona){		
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(Persona.class);
		c.add(Restrictions.eq("datoBasicoByCodigoTipoPersona.codigoDatoBasico",168));
		c.add(Restrictions.eq("estatus", "A"));
		List<Persona> lista = c.list();
		
		return lista;
	}
}
