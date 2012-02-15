package dao.general;

import java.util.List;

import modelo.DatoBasico;
import modelo.PersonaNatural;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoPersonaNatural extends GenericDao {

	public PersonaNatural buscarPersonaNatural(String d ) {
		System.out.println("llego");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(PersonaNatural.class);
		c.add(Restrictions.eq("cedulaRif", d));
		PersonaNatural objectPersona = (PersonaNatural) c.list().get(0);
		return objectPersona;
	}
	

	public List<PersonaNatural> filtrarPersonas(DatoBasico dB, String filtroCI,
			String filtroNombre, String filtroApellido) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonaNatural.class);
		c.add(Restrictions.eq("estatus", 'A'));
		if (filtroCI != "") {
			c.add(Restrictions.eq("cedulaRif", filtroCI + "%"));
		}
		if (filtroNombre != "") {
			c.add(Restrictions.eq("primerNombre", filtroNombre + "%"));
		}
		if (filtroApellido != "") {
			c.add(Restrictions.eq("primerApellido", filtroApellido + "%"));
		}
		c.createCriteria("persona");
		c.add(Restrictions.sqlRestriction("codigo_tipo_persona = "
				+ dB.getCodigoDatoBasico() + ""));
		return (List<PersonaNatural>) c.list();
	}

	public List<PersonaNatural> filtrarPersonasDistintas(DatoBasico dB,
			String filtroCI, String filtroNombre, String filtroApellido) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonaNatural.class);
		c.add(Restrictions.eq("estatus", 'A'));
		if (filtroCI != "") {
			c.add(Restrictions.eq("cedulaRif", filtroCI + "%"));
		}
		if (filtroNombre != "") {
			c.add(Restrictions.eq("primerNombre", filtroNombre + "%"));
		}
		if (filtroApellido != "") {
			c.add(Restrictions.eq("primerApellido", filtroApellido + "%"));
		}
		c.createCriteria("persona");
		c.add(Restrictions.sqlRestriction("codigo_tipo_persona <> "
				+ dB.getCodigoDatoBasico() + ""));

		return (List<PersonaNatural>) c.list();
	}
	
	public PersonaNatural buscar(String cedula) {
		
		PersonaNatural d = new PersonaNatural();
		List<PersonaNatural> a = this.listar(PersonaNatural.class);
		for(int i = 0; i< a.size(); i++){
			if(a.get(i).getCedulaRif().equals(cedula)){
				d = a.get(i);
			}
		}
		
		return d;
	}
	
	public List<PersonaNatural> filtrarPersonal(DatoBasico dB, DatoBasico dB2,
			String filtroCI, String filtroNombre, String filtroApellido) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonaNatural.class);
		c.add(Restrictions.eq("estatus", 'A'));
		if (filtroCI != "") {
			c.add(Restrictions.eq("cedulaRif", filtroCI + "%"));
		}
		if (filtroNombre != "") {
			c.add(Restrictions.eq("primerNombre", filtroNombre + "%"));
		}
		if (filtroApellido != "") {
			c.add(Restrictions.eq("primerApellido", filtroApellido + "%"));
		}
		c.createCriteria("persona");
		c.add(Restrictions.sqlRestriction("codigo_tipo_persona = "
				+ dB.getCodigoDatoBasico() + "OR codigo_tipo_persona = "
				+ dB2.getCodigoDatoBasico()));
		return (List<PersonaNatural>) c.list();
	}

}
