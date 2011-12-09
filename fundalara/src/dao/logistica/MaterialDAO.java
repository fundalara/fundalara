package dao.logistica;

import java.util.List;

import modelo.ClaseMaterial;
import modelo.Material;
import dao.general.GenericDAO;

public class MaterialDAO extends GenericDAO {
	
	public List<Material> listarMateriales() {
		// TODO Auto-generated method stub		
		List<Material> l = getSession().createCriteria(Material.class).list();
		getSession().flush();
		return l;
	}

}
