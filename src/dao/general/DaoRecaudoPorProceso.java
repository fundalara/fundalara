package dao.general;

import java.util.List;

import modelo.DatoBasico;
import modelo.RecaudoPorProceso;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import comun.TipoDatoBasico;

import dao.generico.GenericDao;

/**
 * Clase de acceso y manejo de los datos relacionados a los recaudos solicitados
 * por los procesos de la gestion deportiva
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 18/12/2011
 * 
 */
public class DaoRecaudoPorProceso extends GenericDao {

	/**
	 * Busca los recaudos asociados a un proceso y los filtra segun el tipo de
	 * documento indicado
	 * 
	 * @param proceso
	 *            Tipo de proceso de la gestion (Nuevo ingreso, reingreso...)
	 * @param tipoDocumento
	 *            Tipo de documento (Enum)
	 * @param nombre
	 *            del tipo de documento (academico, personal)
	 * @return lista de recaudos solicitados
	 */
	public List<RecaudoPorProceso> buscarPorProceso(DatoBasico proceso,
			TipoDatoBasico tipoDocumento, String nombre) {

		DaoDatoBasico daoDatobasico = new DaoDatoBasico();
		DatoBasico tipoDoc = daoDatobasico.buscarTipo(tipoDocumento, nombre);
		Session session = this.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria c = session
				.createCriteria(RecaudoPorProceso.class)
				.add(Restrictions.eq("datoBasicoByCodigoProceso", proceso))
				.add(Restrictions.eq("estatus", 'A'))
				.setFetchMode("datoBasicoByCodigoDocumento", FetchMode.JOIN)
				.setFetchMode("datoBasicoByCodigoProceso", FetchMode.JOIN)
				.setFetchMode("datoBasicoByCodigoImportancia", FetchMode.JOIN)
				.setFetchMode("datoBasicoByCodigoDocumento.tipoDato",
						FetchMode.JOIN);
		Criteria c2 = c.createCriteria("datoBasicoByCodigoDocumento").add(
				Restrictions.eq("datoBasico", tipoDoc));
		List<RecaudoPorProceso> lista = c2.list();
		
		return lista;
	}
	
}