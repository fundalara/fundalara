package dao.general;

import java.util.List;


import modelo.DatoMedico;
import modelo.DocumentoAcademico;
import modelo.DocumentoAcademicoId;
import modelo.DocumentoEntregado;
import modelo.DocumentoMedico;
import modelo.DocumentoMedicoId;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.generico.GenericDao;
/**
 * Clase de acceso y manejo de los datos relacionados a los documentos medicos solicitados
 * por los procesos de la gestion deportiva
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 02/01/2012
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
			DocumentoMedicoId docId = new DocumentoMedicoId(id, datoMedico.getCodigoDatoMedico());
			DocumentoMedico docMed = new DocumentoMedico(docId, datoMedico,documentoEntregado, 'A');
			session.save(docMed);
		}
		tx.commit();
	}
}
