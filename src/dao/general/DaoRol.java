package dao.general;

import java.util.List;

import modelo.DatoBasico;
import modelo.Grupo;
import modelo.GrupoUsuario;
import modelo.Rol;
import modelo.RolGrupo;
import modelo.Usuario;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import servicio.implementacion.ServicioRol;

import dao.generico.GenericDao;

public class DaoRol extends GenericDao {

	// public List<Rol> buscarRolesSinAsignar(Grupo grupo) {
	// ServicioRol servicioRol;
	// DaoRol dao = new DaoRol();
	// Session session = getSession();
	// Transaction tx = session.beginTransaction();
	// Criteria c = getSession().createCriteria(Rol.class);
	// c.add(Restrictions.eq("estatus", "A"));
	// c.add(Restrictions
	// .sqlRestriction("nombre <> 'ROLE_USER' AND nombre <> 'ITEM' "));
	// //
	// // c.addOrder(Order.asc("nombre"));
	// // c.addOrder(Order.asc("rol"));
	// //
	// List<Rol> lista = (List<Rol>) c.list();
	//
	// Criteria d = getSession().createCriteria(RolGrupo.class);
	// d.add(Restrictions.eq("grupo", grupo));
	// d.add(Restrictions.eq("estatus", "A"));
	//
	//
	// /*for (int i = 0; i < d.list().size(); i++) {
	// RolGrupo rolGrupo = (RolGrupo) d.list().get(i);
	// int tam = c.list().size();
	// System.out.println(rolGrupo.getRol().getCodigoRol()+" RolGrupo");
	// for (int j = 0; j < lista.size(); j++) {
	// Rol rol = (Rol) lista.get(j);
	// if (rolGrupo.getRol().equals(rol)) {
	// Rol auxRol = rol;
	// int k = 1;
	// lista.remove(j);
	// j--;
	// // do {
	// List<Rol> listaRol = dao.listarPorPadre(auxRol);
	// System.out.println("Entrando en el wihile");
	// System.out.println(listaRol.size());
	// for (int h = 0; h < listaRol.size(); h++) {
	// System.out.println(lista.get(h).getCodigoRol()+" codRol");
	// System.out.println(listaRol.get(h).getCodigoRol()+" RolLista");
	//
	// if (lista.get(h).getCodigoRol() == listaRol.get(h).getCodigoRol()) {
	// System.out.println(lista.get(h).getNombre());
	// lista.remove(h);
	// j--;
	// }
	// //
	// //
	// // auxRol = auxRol.getRol();
	// // } while (auxRol == null);
	//
	// }
	// }
	// }
	// }*/
	//
	// return (List<Rol>) lista;
	// }

	public List<Rol> listarPorPadre(Rol db) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Rol.class);
		List list = c.add(Restrictions.eq("rol", db)).list();
		return list;
	}

	public Rol buscarPorCodigo(int db) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(Rol.class);
		List list = c.add(Restrictions.eq("codigoRol", db)).list();
		if (list.size() == 0) {
			return null;
		} else
			return (Rol) list.get(0);
	}

	public List<Rol> buscarRolesSinAsignar(Grupo grupo) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(Rol.class);
		c.add(Restrictions.eq("estatus", "A"));
		c.add(Restrictions
				.sqlRestriction("nombre <> 'ROLE_USER' AND nombre <> 'ITEM' "));
		c.addOrder(Order.asc("nombre"));
		  
		
		List lista = c.list();

		Criteria d = getSession().createCriteria(RolGrupo.class);
		d.add(Restrictions.eq("grupo", grupo));
		d.add(Restrictions.eq("estatus", "A"));

		for (int i = 0; i < d.list().size(); i++) {
			RolGrupo rolGrupo = (RolGrupo) d.list().get(i);
			int tam = c.list().size();
			for (int j = 0; j < lista.size(); j++) {
				Rol rol = (Rol) lista.get(j);
				if (rolGrupo.getRol().equals(rol)) {
					lista.remove(j);
					j--;
				}
			}
		}

		return (List<Rol>) lista;
	}
}
