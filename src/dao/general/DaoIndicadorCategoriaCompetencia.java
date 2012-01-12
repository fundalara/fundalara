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
	
	public List<Indicador> listarIndicadoresPorCategoria(Categoria c, Competencia comp){
		Session session = getSession();                         
		Transaction tx = session.beginTransaction();  
	
	Criteria d = session.createCriteria(IndicadorCategoriaCompetencia.class);
	
	d.add(Restrictions.eq("categoria.codigoCategoria",c.getCodigoCategoria()));
	d.add(Restrictions.eq("competencia.codigoCompetencia", comp.getCodigoCompetencia()));
	List<Indicador> lista= d.list();
	return lista;
	
	

}
	

}