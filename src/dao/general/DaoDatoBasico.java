package dao.general;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import comun.TipoDatoBasico;

import modelo.CondicionCompetencia;
import modelo.DatoBasico;
import modelo.TipoDato;
import dao.generico.GenericDao;

/**
 * Clase DAO para acceso/manejo de los datos basicos
 * 
 * @author Robert A.
 * @author German L.
 * @author Reinaldo L.
 * @autor Taner M.
 * @autor Alix V.
 * @autor Merielen G
 * @autor Diana S.
 * @version 0.1 12/01/2012
 * 
 */

public class DaoDatoBasico extends GenericDao {

	public List<DatoBasico> buscarPorTipoDato(TipoDato td) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("tipoDato", td));
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}

	public List<DatoBasico> listarPorTipoDeDato(String s) {
		// TODO Auto-generated method stub
		List<DatoBasico> lista = new ArrayList<DatoBasico>();
		for (Object o : this.listar(DatoBasico.class)) {
			DatoBasico db = (DatoBasico) o;
			if (db.getTipoDato().getNombre().equals(s)
					&& db.getEstatus() == 'A')
				lista.add(db);
		}
		return lista;
	}

	public List<DatoBasico> listarPorPadre(String s, Integer i) {
		// TODO Auto-generated method stub
		List<DatoBasico> lista = new ArrayList<DatoBasico>();
		for (Object o : this.listar(DatoBasico.class)) {
			DatoBasico db = (DatoBasico) o;
			if (db.getTipoDato().getNombre().equals(s)
					&& db.getEstatus() == 'A'
					&& db.getDatoBasico().getCodigoDatoBasico() == i)
				lista.add(db);
		}
		return lista;
	}

	public DatoBasico buscarPorCodigo(Integer i) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("codigoDatoBasico", i));
		return (DatoBasico) c.list().get(0);
	}

	public List<DatoBasico> listarParroquias() {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(TipoDato.class);
		TipoDato td = (TipoDato) c.add(Restrictions.eq("nombre", "PARROQUIA"))
				.list().get(0);
		c = session.createCriteria(DatoBasico.class);
		List list = c.add(Restrictions.eq("tipoDato", td)).list();
		return list;
	}

	public List<DatoBasico> listarMunicipios() {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(TipoDato.class);
		TipoDato td = (TipoDato) c.add(Restrictions.eq("nombre", "MUNICIPIO"))
				.list().get(0);
		c = session.createCriteria(DatoBasico.class);
		List list = c.add(Restrictions.eq("tipoDato", td)).list();
		return list;

	}

	public List<DatoBasico> listarMunicipiosPorEstados(DatoBasico db) {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(DatoBasico.class);
		List list = c.add(Restrictions.eq("datoBasico", db)).list();
		return list;

	}

	public List<DatoBasico> listarParroquiasPorMunicipios(DatoBasico db) {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(DatoBasico.class);
		List list = c.add(Restrictions.eq("datoBasico", db)).list();
		return list;
	}

	/**
	 * Busca los registros de un tipo de dato en particular
	 * 
	 * @param tipoDato
	 *            tipo de dato a buscar
	 * @return lista de los datos asociados al tipo de dato suministrado
	 * 
	 */
	public List<DatoBasico> buscar(TipoDatoBasico tipoDato) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("tipoDato.codigoTipoDato", tipoDato.getCodigo()));
		c.add(Restrictions.eq("estatus", "A"));
		List<DatoBasico> lista = c.list();
		tx.commit();
		return lista;
	}

	/**
	 * Busca los datos que tiene como padre el datoBasico suministrado
	 * 
	 * @param datoBasico
	 *            dato del cual se desea buscar sus hijos (relacion de
	 *            dependencia hacia el)
	 * @return lista de datos hijos
	 */
	public List<DatoBasico> buscarPorRelacion(DatoBasico datoBasico) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("datoBasico.codigoDatoBasico",
				datoBasico.getCodigoDatoBasico()));
		c.add(Restrictions.eq("estatus", "A"));
		List<DatoBasico> lista = c.list();
		tx.commit();
		return lista;
	}

	public DatoBasico buscarPorString(String s) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("nombre", s));
		c.add(Restrictions.eq("estatus", 'A'));
		return (DatoBasico) c.list().get(0);
	}

	/**
	 * Busca el dato segun su nombre dentro del tipo indicado
	 * 
	 * @param tipoDato
	 *            tipo de dato a buscar
	 * @param nombre
	 *            Valor constante que define el tipo de dato, con el cual se
	 *            filtrara y ejecutara busqueda
	 * @return dato basico de un tipo especifico o null en caso de no encontralo
	 */
	public DatoBasico buscarTipo(TipoDatoBasico tipoDato, String nombre) {
		Session session = getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Query query = session.createSQLQuery(
				"SELECT * FROM dato_basico WHERE codigo_tipo_dato='"
						+ tipoDato.getCodigo() + "' AND upper(nombre)='"
						+ nombre.toUpperCase() + "' AND estatus='A'")
				.addEntity(DatoBasico.class);
		DatoBasico lista = (DatoBasico) query.uniqueResult();
		tx.commit();
		return lista;
	}

	public List<DatoBasico> listarEstados() {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(TipoDato.class);
		TipoDato td = (TipoDato) c.add(Restrictions.eq("nombre", "ESTADO"))
				.list().get(0);
		c = session.createCriteria(DatoBasico.class);
		List list = c.add(Restrictions.eq("tipoDato", td)).list();
		return list;

	}

	public List<DatoBasico> listarPosiciones() {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(TipoDato.class);
		TipoDato td = (TipoDato) c
				.add(Restrictions.eq("nombre", "TIPO UMPIRE")).list().get(0);
		c = session.createCriteria(DatoBasico.class);
		List list = c.add(Restrictions.eq("tipoDato", td)).list();
		return list;
	}

	public List<DatoBasico> listarOrganizacionCompetencia() {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(TipoDato.class);
		TipoDato td = (TipoDato) c
				.add(Restrictions.eq("nombre", "ORGANIZACION COMPETENCIA"))
				.list().get(0);
		c = session.createCriteria(DatoBasico.class);
		List list = c.add(Restrictions.eq("tipoDato", td)).list();
		return list;

	}

	public List<DatoBasico> listarPosicionesJugadores() {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(TipoDato.class);
		TipoDato td = (TipoDato) c.add(Restrictions.eq("nombre", "POSICION"))
				.list().get(0);
		c = session.createCriteria(DatoBasico.class);
		List list = c.add(Restrictions.eq("tipoDato", td)).list();
		return list;

	}

	public List<DatoBasico> listarTipoCompetencia() {

		/**
		 * @autor Merielen Gaspar
         * @autor Diana Santiago
         * 
		 * Metodo que permite listar los tipos de Competencia, de acuerdo a su estatus.
		 * 
		 * @return List<DatoBasico> Lista que contiene los tipos de Competencia.
		 * 
		 */
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(TipoDato.class);
		c.add(Restrictions.eq("nombre", "TIPO COMPETENCIA"));
		TipoDato td = (TipoDato) c.list().get(0);
		c = session.createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("tipoDato", td));
		c.add(Restrictions.eq("estatus", 'A'));
		return c.list();

	}

	public List<DatoBasico> listarCondicion() {

		/**
		 * @autor Merielen Gaspar
         * @autor Diana Santiago
         * 
		 * Metodo que permite listar las Condiciones segun la clasificacion de una Competencia especifica, de acuerdo a su estatus.
		 * 
		 * @return List<DatoBasico> Lista que contiene las Condiciones segun la clasificacion de una Competencia especifica.
		 * 
		 */
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(TipoDato.class);
		c.add(Restrictions.eq("nombre", "CONDICION COMPETENCIA"));
		TipoDato td = (TipoDato) c.list().get(0);
		c = session.createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("tipoDato", td));
		c.add(Restrictions.eq("estatus", 'A'));
		return c.list();
	}

	public List<DatoBasico> listarTipoDato(String nombre) {

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(TipoDato.class);
		TipoDato td = (TipoDato) c.add(Restrictions.eq("nombre", nombre))
				.list().get(0);
		c = session.createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("estatus", 'A'));

		List list = c.add(Restrictions.eq("tipoDato", td)).list();
		return list;
	}
	
	public List<DatoBasico> buscarPadre(DatoBasico db) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction tx =  session.beginTransaction();
		Criteria c = getSession().createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("datoBasico", db));
		c.add(Restrictions.eq("estatus", "A"));
		return c.list();
	}

	public List<DatoBasico> listarComisiones() {		
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Criteria c= session.createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("tipoDato.codigoTipoDato", 102));
		c.add(Restrictions.eq("estatus", "A"));
		List<DatoBasico> lista = c.list();
		return lista;		
	}
	
	public List<DatoBasico> listarTipoMantenimiento() {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("tipoDato.codigoTipoDato", 108));
		c.add(Restrictions.eq("estatus", "A"));
		List<DatoBasico> lista = c.list();
		return lista;

	}
	
	public List<DatoBasico> listarTipoInstalacion() {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(DatoBasico.class);
		c.add(Restrictions.eq("tipoDato.codigoTipoDato", 100));
		c.add(Restrictions.eq("estatus", "A"));
		List<DatoBasico> lista = c.list();
		return lista;

	}
	
	public List<DatoBasico> listarPersonalForaneo(DatoBasico datoBasico) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();		
        
        Criteria x = session.createCriteria(DatoBasico.class);
        Criterion cr1 = Restrictions.eq("nombre", "UMPIRE");
		Criterion cr2 = Restrictions.eq("nombre", "ANOTADOR");
		
		x.add(Restrictions.or(cr1, cr2));
			
		return (x.list());
    
        
	}
}
