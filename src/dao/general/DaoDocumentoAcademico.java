package dao.general;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.DatoAcademico;
import modelo.DocumentoAcademico;
import modelo.DocumentoAcademicoId;
import modelo.DocumentoEntregado;
import dao.generico.GenericDao;

/**
 * Clase de acceso y manejo de los datos relacionados a los recaudos solicitados
 * por los procesos de la gestion deportiva
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 02/01/2012
 * 
 */
public class DaoDocumentoAcademico extends GenericDao {
	
	/**
	 * Guarda los datos de documentos academicos que han sido entregados de
	 * manera fisica y digital por un jugador en un proceso
	 * 
	 * @param documentos
	 *            documentos entregados
	 * @param datoAcademico
	 *            informacion academica asociada a los documentos
	 */
	public void guardar(List<DocumentoEntregado> documentos,
			DatoAcademico datoAcademico) {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		for (DocumentoEntregado documentoEntregado : documentos) {
			session.save(documentoEntregado);
			Query query = session
					.createSQLQuery("SELECT last_value FROM "+DaoDocumentoEntregado.SECUENCIA);
			int id = Integer.valueOf(query.uniqueResult().toString());
			documentoEntregado.setCodigoDocumentoEntregado(id);
			DocumentoAcademicoId docId = new DocumentoAcademicoId(id, datoAcademico.getCodigoAcademico());
			DocumentoAcademico docAcad = new DocumentoAcademico(docId, documentoEntregado, datoAcademico, 'A');
			session.save(docAcad);
		}
		tx.commit();
	}
	
	
	/**
	 * Actualiza los documentos  entregados
	 * @param documentos lista de documentos entregados a actulizar
	 */
	public void actualizar(List<DocumentoEntregado> documentos) {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		for (DocumentoEntregado documentoEntregado : documentos) {
			session.update(documentoEntregado);
		}
		tx.commit();
	}
	
	
	public List<DocumentoEntregado>  buscarDocumentos(DatoAcademico datoAcademico){
		Session sesion = getSession();
		Transaction tx = sesion.beginTransaction();
		Criteria c = sesion.createCriteria(DocumentoAcademico.class)
				.add(Restrictions.eq("datoAcademico", datoAcademico))
				.add(Restrictions.eq("estatus", 'A'));
		List<DocumentoEntregado>  aux =  new ArrayList<DocumentoEntregado>();
		List<DocumentoAcademico>  auxAcademico =  c.list();
		for (DocumentoAcademico documentoAcademico : auxAcademico) {
			aux.add(documentoAcademico.getDocumentoEntregado());
		}
		
		return aux;
		
	}
}
