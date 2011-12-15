package dao.logistica;

import java.util.List;

import modelo.ClaseMaterial;
import modelo.Material;
import modelo.TipoMaterial;
import dao.general.GenericDAO;

public class MaterialDAO extends GenericDAO {
	
	public List<Material> listarMateriales() {
		
		List<Material> listaMateriales = getSession().createQuery("FROM Material WHERE estatus = 'A'").list();				
		getSession().flush();
		getSession().close();
		return listaMateriales;
	}

}
