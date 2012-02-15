package dao.general;

import java.util.ArrayList;
import java.util.List;

import modelo.DocumentoEntregado;
import modelo.DocumentoPersonal;
import modelo.DocumentoPersonalId;
import modelo.Jugador;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

/**
 * Clase de acceso y manejo de los datos relacionados a los documentos personales  de los
 * jugadores
 * 
 * @author Robert A
 * @author German L
 * @version 0.2 30/01/2012
 * 
 */
public class DaoDocumentoPersonal extends GenericDao {

	/**
	 * Guarda los documentos persnales de un jugador
	 * @param documentos a registrar
	 * @param jugador - jugador asociado a los documentos
	 */
	public void guardar(List<DocumentoEntregado> documentos,
			Jugador jugador) {
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		for (DocumentoEntregado documentoEntregado : documentos) {
			session.save(documentoEntregado);
			Query query = session
					.createSQLQuery("SELECT last_value FROM "+DaoDocumentoEntregado.SECUENCIA);
			int id = Integer.valueOf(query.uniqueResult().toString());
			documentoEntregado.setCodigoDocumentoEntregado(id);
			DocumentoPersonalId docId = new DocumentoPersonalId(id, jugador.getCedulaRif());
			DocumentoPersonal docPersonal = new DocumentoPersonal(docId, documentoEntregado, jugador,'A');
			session.save(docPersonal);
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
	
	public List<DocumentoEntregado> buscarDocumentos(Jugador jugador){
			Session sesion = getSession();
			Transaction tx = sesion.beginTransaction();
			Criteria c = sesion.createCriteria(DocumentoPersonal.class)
					.add(Restrictions.eq("jugador", jugador))
					.add(Restrictions.eq("estatus", 'A'));
			List<DocumentoEntregado>  aux =  new ArrayList<DocumentoEntregado>();
			List<DocumentoPersonal>  auxPersonal =  c.list();
			for (DocumentoPersonal documentoPersonal : auxPersonal) {
				aux.add(documentoPersonal.getDocumentoEntregado());
			}
			return aux;
	}

}