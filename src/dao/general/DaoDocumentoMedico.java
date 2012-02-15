package dao.general;

import java.util.ArrayList;
import java.util.List;

import modelo.DatoMedico;
import modelo.DocumentoEntregado;
import modelo.DocumentoMedico;
import modelo.DocumentoMedicoId;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;
/**
 * Clase de acceso y manejo de los datos relacionados a los documentos medicos solicitados
 * por los procesos de la gestion deportiva
 * 
 * @author Robert A
 * @author German L
 * @version 0.2 30/01/2012
 * 
 */
public class DaoDocumentoMedico extends GenericDao {
	/**
	 * Guarda los datos de documentos medicos que han sido entregados de
	 * manera fisica y digital por un jugador en un proceso
	 * 
	 * @param documentos
	 *            documentos entregados
	 * @param datoMedico
	 *            informacion médica asociada a los documentos
	 */
	public void guardar(List<DocumentoEntregado> documentos,
			DatoMedico datoMedico) {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		for (DocumentoEntregado documentoEntregado : documentos) {
			session.save(documentoEntregado);
			Query query = session
					.createSQLQuery("SELECT last_value FROM "+DaoDocumentoEntregado.SECUENCIA);
			int id = Integer.valueOf(query.uniqueResult().toString());
			documentoEntregado.setCodigoDocumentoEntregado(id);
			DocumentoMedicoId docId = new DocumentoMedicoId(id, datoMedico.getCodigoDatoMedico());
			DocumentoMedico docMed = new DocumentoMedico(docId, datoMedico,documentoEntregado, 'A');
			session.save(docMed);
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
	
	public List<DocumentoEntregado>  buscarDocumentos(DatoMedico datoMedico){
		Session sesion = getSession();
		Transaction tx = sesion.beginTransaction();
		Criteria c = sesion.createCriteria(DocumentoMedico.class)
				.add(Restrictions.eq("datoMedico", datoMedico))
				.add(Restrictions.eq("estatus", 'A'));
		List<DocumentoEntregado>  aux =  new ArrayList<DocumentoEntregado>();
		List<DocumentoMedico>  auxMedico =  c.list();
		for (DocumentoMedico documentoMedico : auxMedico) {
			aux.add(documentoMedico.getDocumentoEntregado());
		}
		return aux;
	}
	
}