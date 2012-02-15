package dao.general;

import java.util.List;

import modelo.Grupo;
import modelo.Rol;
import modelo.RolGrupo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoRolGrupo extends GenericDao {
	public List<RolGrupo> buscarRolesPorGrupo(Grupo grupo) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(RolGrupo.class);
		c.add(Restrictions.eq("grupo", grupo));
		c.add(Restrictions.eq("estatus", "A"));
		c.createCriteria("rol");
		c.add(Restrictions.sqlRestriction("nombre <> 'ROLE_USER' AND nombre <> 'ITEM' "));
		
		return (List<RolGrupo>) c.list();
	}
	
	
	public RolGrupo buscarRolExistente(Rol rol, Grupo grupo) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(RolGrupo.class);
		c.add(Restrictions.eq("grupo", grupo));
		c.add(Restrictions.eq("rol", rol));
		if (c.list().size() == 0){
			return null;
		} else 
		return (RolGrupo) c.list().get(0);
	}
}
