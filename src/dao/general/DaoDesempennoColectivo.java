package dao.general;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import modelo.DesempennoColectivo;
import modelo.EquipoJuego;
import modelo.IndicadorCategoriaCompetencia;
import dao.generico.GenericDao;

public class DaoDesempennoColectivo extends GenericDao {
	
	public DesempennoColectivo buscarCarrerasPorEquipo(EquipoJuego ej,IndicadorCategoriaCompetencia icc,int inning){
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = session.createCriteria(DesempennoColectivo.class);
		c.add(Restrictions.eq("equipoJuego",ej));
		c.add(Restrictions.eq("indicadorCategoriaCompetencia",icc));
		c.add(Restrictions.eq("inning", inning));
		return (DesempennoColectivo) c.uniqueResult();
		
	}
		
	}
