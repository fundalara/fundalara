package dao.general;

import dao.generico.GenericDao;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.generico.GenericDao;
import modelo.DatoBasico;
import modelo.DocumentoAcreedor;
import modelo.IngresoInscripcion;
import modelo.Persona;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
public class DaoDocumentoAcreedor extends GenericDao {
	public DocumentoAcreedor buscarPorCodigo(int td) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(DocumentoAcreedor.class);
		c.add(Restrictions.eq("codigoDocumentoAcreedor", td));
		c.add(Restrictions.eq("estatus", "A"));
		if (c.list().size()==0){
			return null;
		} else {
			return (DocumentoAcreedor) c.list().get(0);
		}
	}
	public List <DocumentoAcreedor> buscarPendientesPorRif(Persona td) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(DocumentoAcreedor.class);
		c.add(Restrictions.eq("personaByCedulaRif", td));
		c.add(Restrictions.eq("estatus", "A"));
		c.add(Restrictions.eq("estado", "P"));
		return c.list();
	}
	
	public List <DocumentoAcreedor> buscarPendientesPorRifAtleta(Persona td) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		d.parse("" + d.getDate() + "/" + (d.getMonth() + 1) + "/"
				+ (d.getYear() + 1900));
		System.out.println(d);
		Criteria c = getSession().createCriteria(DocumentoAcreedor.class);
		c.add(Restrictions.eq("personaByCedulaRif", td)).add(Restrictions.eq("estado", 'P')).add(Restrictions.sqlRestriction("fecha_vencimiento < '"+d+"'"));
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}
	public List <DocumentoAcreedor> buscarAdelantosPorRifAtleta(Persona td) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		
		Criteria c = getSession().createCriteria(DocumentoAcreedor.class);
		c.add(Restrictions.eq("personaByCedulaAtleta", td)).add(Restrictions.eq("estado", 'P'));
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}
	
	
	
	public List <DocumentoAcreedor> buscarAdelantosPorRif(Persona td) {
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		
		Criteria c = getSession().createCriteria(DocumentoAcreedor.class);
		c.add(Restrictions.eq("personaByCedulaRif", td)).add(Restrictions.eq("estado", 'P'));
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}
	
	
	public List<DocumentoAcreedor> buscarPorTipoIngreso(String tipoIngreso){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(DocumentoAcreedor.class);
		c.createCriteria("tipoIngreso").add(Restrictions.eq("descripcion", tipoIngreso));
		c.add(Restrictions.eq("estatus", "A"));
		if (c.list().size()==0){
			return null;
		} else {
			return c.list();
		}

	}
	
	public List<DocumentoAcreedor> buscarFiltrado(String tipoIngreso, String estado, Date fechaIni, Date fechaFin){
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(DocumentoAcreedor.class);
		c.createCriteria("tipoIngreso").add(Restrictions.eq("descripcion", tipoIngreso));
		c.add(Restrictions.eq("estatus", "A"));
		c.add(Restrictions.eq("estado",estado));
		c.add(Restrictions.between("fechaEmision", new Date(fechaIni.getTime()), new Date(fechaFin.getTime())));
		if (c.list().size()==0){
			return null;
		} else {
			return c.list();
		}
	}
	


}
