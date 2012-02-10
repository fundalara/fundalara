package dao.general;

import java.util.List;

import modelo.Categoria;
import modelo.Competencia;
import modelo.EquipoCompetencia;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoEquipoCompetencia extends GenericDao {
	
	//Metodos personalizados
	public List<EquipoCompetencia> listarActivos(){
		//Permite buscar todas las divisas con estatus = 'A'
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(EquipoCompetencia.class);
		List lista = c.add(Restrictions.eq("estatus",'A')).list();
		return lista;
	}
	

public List<EquipoCompetencia> buscarEquipoporCompetencia(Competencia c) {
		Session session = this.getSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Criteria cc = session.createCriteria(EquipoCompetencia.class);
		cc.add(Restrictions.eq("competencia", c));
		cc.add(Restrictions.eq("estatus", 'A'));
		List<EquipoCompetencia> lista = cc.list();
		return lista;
	}
	
	
	
//Para calendario..
	public List listarEquipoPorCategoria(Class o, Categoria codigo){
		Session session = getSession(); 
		Transaction tx =  session.beginTransaction();
		Criteria c = session.createCriteria(EquipoCompetencia.class);
		Criteria c1 = c.createCriteria("equipo");
		c1.add(Restrictions.eq("categoria", codigo));	
		List <EquipoCompetencia> lista = c.list(); 
		return lista;
	}

}
