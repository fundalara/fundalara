package dao.general;

import dao.generico.GenericDao;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.CuentaPagar;
import modelo.DatoBasico;

public class DaoCuentaPagar extends GenericDao {
	
	public CuentaPagar buscarOrigen(String cp){	
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(CuentaPagar.class);
		c.add(Restrictions.eq("origen",cp)).list();
		if (c.list().size()==0){
			return null;
		} else 
		return (CuentaPagar)c.list().get(0);
		
	}
	
	public List<CuentaPagar> listarCuentaPorPagar(DatoBasico dato) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(CuentaPagar.class);
		c.add(Restrictions.eq("estatus",'A')).add(Restrictions.eq("estado",'P')).add(Restrictions.eq("datoBasicoByCodigoTipoEgreso", dato)).list();
		List<CuentaPagar> list = (List<CuentaPagar>) c.list();
		return list;
	}
	
	public List<CuentaPagar> listarCuentaPorPagarPorFecha(DatoBasico dato, String inicio, String fin, String filtro) throws ParseException {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(CuentaPagar.class);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date startDate = (Date)format.parse(inicio);
		Date endDate = (Date)format.parse(fin);
		if (filtro.equals("A")){
			c.add(Restrictions.eq("estatus",'A')).add(Restrictions.eq("datoBasicoByCodigoTipoEgreso", dato)).add(Restrictions.between("fechaEmision", new Date(startDate.getTime()), new Date(endDate.getTime()))).list();
		}else{
			c.add(Restrictions.eq("estatus",'A')).add(Restrictions.eq("estado",filtro)).add(Restrictions.eq("datoBasicoByCodigoTipoEgreso", dato)).add(Restrictions.between("fechaEmision", new Date(startDate.getTime()), new Date(endDate.getTime()))).list();
		}
		
		List<CuentaPagar> list = (List<CuentaPagar>) c.list();
		return list;
	}
	
	public List<CuentaPagar> listarCuentaPorPagarFiltro(DatoBasico dato,String filtro) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(CuentaPagar.class);
		if (filtro.equals("A")){
			c.add(Restrictions.eq("estatus",'A')).add(Restrictions.eq("datoBasicoByCodigoTipoEgreso", dato)).list();
		}else{
			c.add(Restrictions.eq("estatus",'A')).add(Restrictions.eq("estado",filtro)).add(Restrictions.eq("datoBasicoByCodigoTipoEgreso", dato)).list();
		}
		List<CuentaPagar> list = (List<CuentaPagar>) c.list();
		return list;
	}


}
