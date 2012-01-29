package dao.general;

import java.util.List;

import modelo.Categoria;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.Indicador;
import modelo.IndicadorCategoriaCompetencia;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoIndicadorCategoriaCompetencia extends GenericDao {

	public List<IndicadorCategoriaCompetencia> listarIndicadoresIndividualesPorCategoria(Categoria c,
			Competencia comp, DatoBasico modalidad) {
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria d = session.createCriteria(DatoBasico.class);
		d.add(Restrictions.eq("nombre","INDIVIDUAL"));
		DatoBasico medicion = (DatoBasico) d.list().get(0);
		
		d = session.createCriteria(IndicadorCategoriaCompetencia.class);
		d.add(Restrictions.eq("categoria",c));
		d.add(Restrictions.eq("competencia",comp));
		
		Criteria c2 = d.createCriteria("indicador");
		c2.add(Restrictions.eq("datoBasicoByCodigoModalidad", modalidad));
		c2.add(Restrictions.eq("datoBasicoByCodigoMedicion",  medicion));
		List<IndicadorCategoriaCompetencia> lista = d.list();
		return lista;

	}
	
	public List<IndicadorCategoriaCompetencia> listarIndicadoresColectivosPorCategoria(Categoria c,
			Competencia comp, DatoBasico modalidad) {
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		
		Criteria d = session.createCriteria(DatoBasico.class);
		d.add(Restrictions.eq("nombre","COLECTIVO"));
		DatoBasico medicion = (DatoBasico) d.list().get(0);
		
		d = session.createCriteria(IndicadorCategoriaCompetencia.class);
		d.add(Restrictions.eq("categoria",c));
		d.add(Restrictions.eq("competencia",comp));
		
		Criteria c2 = d.createCriteria("indicador");
		c2.add(Restrictions.eq("datoBasicoByCodigoModalidad", modalidad));
		c2.add(Restrictions.eq("datoBasicoByCodigoMedicion",  medicion));
		List<IndicadorCategoriaCompetencia> lista = d.list();
		return lista;

	}

}