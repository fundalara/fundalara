package dao.general;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import modelo.DatoBasico;
import modelo.Equipo;
import modelo.Horario;
import modelo.Personal;
import modelo.PersonalCargo;
import modelo.PersonalEquipo;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoPersonalEquipo extends GenericDao {

	public List<PersonalEquipo> buscarEquipo(Personal p) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonalEquipo.class);
		c.add(Restrictions.eq("personal", p));
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}

	public List<PersonalEquipo> buscarPersonal(Equipo eq, Date fecha) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonalEquipo.class);
		c.add(Restrictions.eq("equipo", eq));
		c.add(Restrictions.ge("fechaFinalizacion", fecha));
		c.add(Restrictions.le("fechaInicio", fecha));
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}
	
public List<PersonalEquipo> listarPersonal(Integer codigo){
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(PersonalEquipo.class);
		List lista = c.add(Restrictions.eq("equipo.codigoEquipo",codigo)).list();
		return lista;
	}

}
