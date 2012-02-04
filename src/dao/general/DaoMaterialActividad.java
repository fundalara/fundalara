package dao.general;

import java.util.ArrayList;
import java.util.List;

import modelo.Actividad;
import modelo.MaterialActividad;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.generico.GenericDao;

public class DaoMaterialActividad extends GenericDao {
	
	public List listarActividad(Actividad a) {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria c = getSession().createCriteria(MaterialActividad.class);
		List lista = c.add(Restrictions.eq("actividad", a)).list();
		return lista;
	}

	public List<MaterialActividad> listarPorActividad(Actividad actividad) {
		
		List<MaterialActividad> a = this.listar(MaterialActividad.class);
		List<MaterialActividad> b = new ArrayList<MaterialActividad>();
		
		for(int i = 0; i < a.size(); i++){
			if(a.get(i).getActividad().getCodigoActividad() == actividad.getCodigoActividad()){
				b.add(a.get(i));
			}
		}
		
		
		
		return b;
	}

}
