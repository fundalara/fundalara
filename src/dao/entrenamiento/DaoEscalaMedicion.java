package dao.entrenamiento;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import modelo.EscalaMedicion;
import dao.general.GenericDAO;

public class DaoEscalaMedicion extends GenericDAO {

	public List <EscalaMedicion> filtro(Character estatus){
		
		@SuppressWarnings("unchecked")
		List<EscalaMedicion> list = ((List<EscalaMedicion>)getSession().createCriteria(EscalaMedicion.class).add(
				Restrictions.eq("estatus", estatus)).list());
		return list;
	}
}
