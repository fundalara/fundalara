package dao.general;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.DatoBasico;
import modelo.DesempennoIndividual;
import modelo.IndicadorCategoriaCompetencia;
import modelo.LineUp;
import modelo.Roster;
import dao.generico.GenericDao;

public class DaoDesempennoIndividual extends GenericDao {

	public DesempennoIndividual obtenerDesempennoPorIndicador(
			IndicadorCategoriaCompetencia icc, LineUp lineUp) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(DesempennoIndividual.class);
		c.add(Restrictions.eq("indicadorCategoriaCompetencia", icc));
		c.add(Restrictions.eq("lineUp", lineUp));
		return (DesempennoIndividual) c.uniqueResult();
	}

	public List<DesempennoIndividual> obtenerDesempennoJugador(LineUp lineUp) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(DesempennoIndividual.class);
		c.add(Restrictions.eq("lineUp", lineUp));
		return c.list();
	}

	/**
	 * Obtiene valor acumulado de indicadores deportivos segun categoria actual
	 * del jugador
	 * 
	 * @param datoBasico
	 *            Indica la modalidad deportiva (Ofensiva, Defensiva, Pitcheo)
	 * @param roster
	 *            codigo del roster al que pertenece el jugador
	 * @return Lista de indicadores acumulados del jugador
	 */
	public List<Object> calcularDesempenno(DatoBasico datoBasico, Roster r) {
		Session session = this.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Query query = session
				.createSQLQuery("SELECT distinct i.abreviatura, SUM(d.valor) FROM indicador i,desempenno_individual d, roster_competencia rc,line_up l,indicador_categoria_competencia ic"
						+ " WHERE rc.codigo_roster="
						+ r.getCodigoRoster()
						+ "AND l.codigo_roster_competencia= rc.codigo_roster_competencia "
						+ "AND i.codigo_modalidad="
						+ datoBasico.getCodigoDatoBasico()
						+ ""
						+ " AND ic.codigo_categoria= "
						+ r.getEquipo().getCategoria().getCodigoCategoria()
						+ ""
						+ "AND ic.codigo_indicador=i.codigo_indicador"
						+ " AND d.codigo_indicador_categoria_competencia=ic.codigo_indicador_categoria_competencia"
						+ " AND d.codigo_line_up= l.codigo_line_up GROUP BY i.abreviatura");
		List<Object> lista = query.list();
		tx.commit();
		return lista;
	}

}