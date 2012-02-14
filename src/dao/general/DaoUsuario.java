package dao.general;

import java.util.List;

import modelo.Grupo;
import modelo.GrupoUsuario;
import modelo.Usuario;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoUsuario extends GenericDao {

	public Usuario buscarPorNombre(String nombre){
		Session session = getSession();
		session.beginTransaction();
		Criteria c = session.createCriteria(Usuario.class);
		c.add(Restrictions.eq("nick",nombre));
		c.add(Restrictions.eq("estatus",'A'));
		return (Usuario) c.uniqueResult();
	}
	
	public List<Usuario> buscarUsuariosSinAsignar(Grupo grupo) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("estatus", "A"));
		List lista = c.list();
		
		Criteria d = getSession().createCriteria(GrupoUsuario.class);
		d.add(Restrictions.eq("grupo", grupo));
		d.add(Restrictions.eq("estatus", "A"));
		
		for (int i = 0; i < d.list().size(); i++) {
			GrupoUsuario grupoUsuario = (GrupoUsuario) d.list().get(i);
			int tam = c.list().size();
			for (int j = 0; j < lista.size(); j++) {
				Usuario usuario = (Usuario) lista.get(j);
				if (grupoUsuario.getUsuario().equals(usuario)) {
					lista.remove(j);
					j--;
				}
			}
		}

		return (List<Usuario>) lista;
	}
}
