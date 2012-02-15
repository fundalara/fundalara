package dao.general;

import java.util.List;

import modelo.Grupo;
import modelo.GrupoUsuario;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoGrupoUsuario extends GenericDao {

	public List<GrupoUsuario> buscarUsuariosPorGrupo(Grupo grupo) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(GrupoUsuario.class);
		c.add(Restrictions.eq("grupo", grupo));
		c.add(Restrictions.eq("estatus", 'A'));
		
		return (List<GrupoUsuario>) c.list();
	}
}
