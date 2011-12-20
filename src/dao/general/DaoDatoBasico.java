package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import modelo.DatoBasico;
import modelo.TipoDato;
import dao.generico.GenericDao;

public class DaoDatoBasico extends GenericDao {

	public List<DatoBasico> buscarPorTipoDato(TipoDato td) {
		// TODO Auto-generated method stub
		Criteria c = getSession().createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("tipoDato", td));
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}
	
	public DatoBasico buscarPorCodigo(String td) {
		// TODO Auto-generated method stub
		Criteria c = getSession().createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("codigoDatoBasico", td));
		return (DatoBasico) c.list().get(0);
	}
}
