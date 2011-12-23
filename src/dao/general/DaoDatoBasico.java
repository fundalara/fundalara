package dao.general;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.DatoBasico;
import modelo.TipoDato;
import dao.generico.GenericDao;

public class DaoDatoBasico extends GenericDao {

	public List<DatoBasico> buscarPorTipoDato(TipoDato td) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
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
}
