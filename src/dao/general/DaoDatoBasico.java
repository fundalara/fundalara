package dao.general;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import comun.TipoDatoBasico;

import modelo.DatoBasico;
import modelo.TipoDato;
import dao.generico.GenericDao;

public class DaoDatoBasico extends GenericDao {

	public List<DatoBasico> buscarPorTipoDato(TipoDato td) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("tipoDato", td));
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}

	public List<DatoBasico> listarPorTipoDeDato(String s) {
		// TODO Auto-generated method stub
		List<DatoBasico> lista = new ArrayList<DatoBasico>();
		for (Object o : this.listar(DatoBasico.class)) {
			DatoBasico db = (DatoBasico)o;
			if(db.getTipoDato().getNombre().equals(s)&&db.getEstatus()=='A')
				 lista.add(db);
		}
		return lista;
	}
	
	public List<DatoBasico> listarPorPadre(String s, Integer i) {
		// TODO Auto-generated method stub
		List<DatoBasico> lista = new ArrayList<DatoBasico>();
		for (Object o : this.listar(DatoBasico.class)) {
			DatoBasico db = (DatoBasico)o;
			if(db.getTipoDato().getNombre().equals(s)&&db.getEstatus()=='A'&&db.getDatoBasico().getCodigoDatoBasico()==i)
				 lista.add(db);
		}
		return lista;
	}
	
	public DatoBasico buscarPorCodigo(Integer i) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("codigoDatoBasico", i));
		return (DatoBasico) c.list().get(0);
	}

	public List<DatoBasico> listarParroquias() {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(TipoDato.class);
		TipoDato td = (TipoDato) c.add(Restrictions.eq("nombre", "PARROQUIA"))
				.list().get(0);
		c = session.createCriteria(DatoBasico.class);
		List list = c.add(Restrictions.eq("tipoDato", td)).list();
		return list;
	}
	
	/**
	 * Busca los registros de un tipo de dato en particular
	 * @param tipoDato tipo de dato a buscar
	 * @return lista de los datos asociados al tipo de dato suministrado
	 * 
	 */
	public List<DatoBasico> buscar(TipoDatoBasico tipoDato){		
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("tipoDato.codigoTipoDato", tipoDato.getCodigo()));
		c.add(Restrictions.eq("estatus", "A"));
		List<DatoBasico> lista = c.list();
		return lista;
	}
	
	/**
	 * Busca los datos que tiene como padre el datoBasico suministrado
	 * @param datoBasico dato del cual se desea buscar sus hijos (relacion de dependencia hacia el)
	 * @return lista de datos hijos
	 */
	public List<DatoBasico> buscarPorRelacion(DatoBasico datoBasico){		
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("datoBasico.codigoDatoBasico",datoBasico.getCodigoDatoBasico()));
		c.add(Restrictions.eq("estatus", "A"));
		List<DatoBasico> lista = c.list();
		return lista;
	}
	
	
	public DatoBasico buscarPorString(String s) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("nombre", s));
		c.add(Restrictions.eq("estatus", 'A'));
		return (DatoBasico) c.list().get(0);
	}
	
	
	
}
