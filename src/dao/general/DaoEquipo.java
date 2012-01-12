package dao.general;

import java.util.List;

import modelo.Categoria;
import modelo.DatoBasico;
import modelo.Equipo;

import org.apache.commons.collections.ResettableIterator;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoEquipo extends GenericDao {
	
	public List listarEquipoPorCategoria(Class o, int codigo){
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(Equipo.class);
		c.add(Restrictions.eq("categoria.codigoCategoria",codigo));
		List <Equipo> lista = c.list(); 
		return lista;
	}
	
	public List listarEquipoForaneos(){
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(Equipo.class);
		c.add(Restrictions.not(Restrictions.eq("divisa.codigoDivisa",1)));
		List <Equipo> lista = c.list();	
		return lista;
	}

}