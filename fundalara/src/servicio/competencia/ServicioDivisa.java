package servicio.competencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import dao.competencia.DaoDivisa;

import modelo.Divisa;

public class ServicioDivisa implements IServicioDivisa {
    
	DaoDivisa daoDivisa;
	
	public DaoDivisa getDaoDivisa() {
		return daoDivisa;
	}

	public void setDaoDivisa(DaoDivisa daoDivisa) {
		this.daoDivisa = daoDivisa;
	}

	@Override
	public void eliminar(Divisa d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(Divisa d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(Divisa d) {
		// TODO Auto-generated method stub

	}

	@Override
	public Divisa buscarPorCodigo(Divisa d) {
		// TODO Auto-generated method stub
		
		return (Divisa) daoDivisa.getSession().createCriteria(Divisa.class).add(Restrictions.eq("codigoDivisa",d.getCodigoDivisa())).list().get(0);
		//Criteria c = daoDivisa.getSession().createCriteria("modelo.Divisa");
		//c.add(a)
		//System.out.println(d);
		//System.out.println(daoDivisa.getSession().load(Divisa.class, d.getCodigoDivisa()));
		//List lista = daoDivisa.getSession().createCriteria(Divisa.class).add(Restrictions.eq("codigoDivisa",d.getCodigoDivisa())).list();
		
		//return (Divisa) lista.get(0);
		//c.add(Restrictions.eq("nombre del campo en la bd", valor del objeto que se pasa por parámetro))
	}

}
