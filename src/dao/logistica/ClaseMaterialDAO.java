package dao.logistica;

import java.util.List;

import modelo.ClaseMaterial;
import dao.general.GenericDAO;

public class ClaseMaterialDAO extends GenericDAO {
	
	public List<ClaseMaterial> listarClasesMateriales() {
		// TODO Auto-generated method stub		
		List<ClaseMaterial> l = getSession().createCriteria(ClaseMaterial.class).list();
		getSession().close();
		return l;
	}
	

	
    public ClaseMaterial obtenerClasePorCodigo(String code){
		
		ClaseMaterial cmat = null;
		
		try{
			cmat = (ClaseMaterial) getSession().get(ClaseMaterial.class,code);
			getSession().close();
			return cmat;
		}catch(Exception e){
			e.printStackTrace();
		}
		getSession().close();
		return cmat;
	}

}
