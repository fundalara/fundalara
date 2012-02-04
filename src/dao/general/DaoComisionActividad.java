package dao.general;

import java.util.ArrayList;
import java.util.List;

import modelo.Actividad;
import modelo.ComisionActividad;
import dao.generico.GenericDao;

public class DaoComisionActividad extends GenericDao {

	public List<ComisionActividad> listar(Actividad actividad) {
		
		List<ComisionActividad> a = this.listar(ComisionActividad.class);
		List<ComisionActividad> b = new ArrayList<ComisionActividad>();
		
		for(int i = 0; i < a.size(); i++){
			if(a.get(i).getActividad().getCodigoActividad() == actividad.getCodigoActividad()){
				b.add(a.get(i));
			}
		}
		
		
		return b;
		
	}

}
