package dao.general;

import java.util.List;

import modelo.DatoBasico;
import modelo.PersonaJuridica;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoPersonaJuridica extends GenericDao {

	
	public PersonaJuridica buscarPorCedulaRif(String d){	
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonaJuridica.class);
		c.add(Restrictions.eq("cedulaRif",d)).list();
		return (PersonaJuridica)c.list().get(0);
	}
	
	public List<PersonaJuridica> filtrarPersonas(DatoBasico dB,
			String filtroCI, String filtroRazon) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonaJuridica.class);
		c.add(Restrictions.eq("estatus", 'A'));
		if (filtroCI != "") {
			c.add(Restrictions.eq("cedulaRif", filtroCI + "%"));
		}
		if (filtroRazon != "") {
			c.add(Restrictions.eq("razonSocial", filtroRazon + "%"));
		}
		c.createCriteria("persona");
		c.add(Restrictions.sqlRestriction("codigo_tipo_persona = "
				+ dB.getCodigoDatoBasico() + ""));
		return (List<PersonaJuridica>) c.list();
	}

	public List<PersonaJuridica> filtrarPersonasDistintas(String filtroCI,
			String filtroRazon) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonaJuridica.class);
		c.add(Restrictions.eq("estatus", 'A'));
		if (filtroCI != "") {
			c.add(Restrictions.eq("cedulaRif", filtroCI + "%"));
		}
		if (filtroRazon != "") {
			c.add(Restrictions.eq("razonSocial", filtroRazon + "%"));
		}

		return (List<PersonaJuridica>) c.list();
	}
	
	public PersonaJuridica buscarByRazonSocial(String d){	
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonaJuridica.class);
		c.add(Restrictions.eq("razonSocial",d)).list();
		return (PersonaJuridica)c.list().get(0);
	}
}
