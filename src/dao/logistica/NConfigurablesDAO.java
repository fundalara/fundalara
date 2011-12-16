package dao.logistica;

import java.util.List;

import modelo.Comision;
import modelo.TipoMaterial;
import dao.general.GenericDAO;

public class NConfigurablesDAO extends GenericDAO {
	
	public List<Comision> listarComisiones() {
		// TODO Auto-generated method stub
		List<Comision> l = getSession().createQuery("FROM Comision WHERE estatus = 'A'").list();
		getSession().flush();
		return l;
	}
	
	
}
