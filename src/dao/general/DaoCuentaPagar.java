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
import modelo.Persona;

public class DaoCuentaPagar extends GenericDao {
	
	public CuentaPagar buscarNumeroDocumento(String cp){	
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(CuentaPagar.class);
		c.add(Restrictions.eq("numeroDocumento",cp)).list();
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
	
	public List<CuentaPagar> listarCuentaPorPagarPorFechaPersona(String inicio, String fin, String filtro,Persona persona) throws ParseException {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(CuentaPagar.class);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date startDate = (Date)format.parse(inicio);
		Date endDate = (Date)format.parse(fin);
		if (filtro.equals("A")){
			c.add(Restrictions.eq("persona", persona)).add(Restrictions.eq("estatus",'A')).add(Restrictions.between("fechaEmision", new Date(startDate.getTime()), new Date(endDate.getTime()))).list();
		}else{
			c.add(Restrictions.eq("persona", persona)).add(Restrictions.eq("estatus",'A')).add(Restrictions.eq("estado",filtro)).add(Restrictions.between("fechaEmision", new Date(startDate.getTime()), new Date(endDate.getTime()))).list();
		}
		
		List<CuentaPagar> list = (List<CuentaPagar>) c.list();
		return list;
	}
	
	public List<CuentaPagar> listarCuentaPorPagarFiltroPersona(String filtro,Persona persona) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(CuentaPagar.class);
		if (filtro.equals("A")){
			c.add(Restrictions.eq("persona", persona)).add(Restrictions.eq("estatus",'A')).list();
		}else{
			c.add(Restrictions.eq("persona", persona)).add(Restrictions.eq("estatus",'A')).add(Restrictions.eq("estado",filtro)).list();
		}
		List<CuentaPagar> list = (List<CuentaPagar>) c.list();
		return list;
	}
	public List<CuentaPagar> buscarPendientesPorRif(Persona td) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(CuentaPagar.class);		
		c.add(Restrictions.eq("persona", td));
		c.add(Restrictions.eq("estatus", "A"));
		c.add(Restrictions.eq("estado", "P"));
		return (List<CuentaPagar>) c.list();
	}
	
	public List<CuentaPagar> buscarPendienteporPersona(String nombre){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(CuentaPagar.class).add(Restrictions.eq("estatus", "A")).
				add(Restrictions.eq("estado", "P")).createCriteria("persona").
				createCriteria("datoBasicoByCodigoTipoPersona").add(Restrictions.eq("nombre", nombre));
		return (List<CuentaPagar>) c.list();
	}
	
	public List<CuentaPagar> BuscarPorPersona(Persona persona) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(CuentaPagar.class);
		c.add(Restrictions.eq("persona", persona))
				.add(Restrictions.eq("estatus", 'A')).list();
		return (List<CuentaPagar>) c.list();
	}
	
	public List<CuentaPagar> listarPorFecha(String inicio, String fin, Persona persona) throws ParseException {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(CuentaPagar.class);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date startDate = (Date)format.parse(inicio);
		Date endDate = (Date)format.parse(fin);
		c.add(Restrictions.eq("persona", persona)).add(Restrictions.eq("estatus",'A')).add(Restrictions.between("fechaEmision", new Date(startDate.getTime()), new Date(endDate.getTime()))).list();
		
		
		List<CuentaPagar> list = (List<CuentaPagar>) c.list();
		return list;
	}


}
