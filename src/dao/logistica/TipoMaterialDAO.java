package dao.logistica;

import java.util.List;

import modelo.TipoMaterial;
import dao.general.GenericDAO;

public class TipoMaterialDAO extends GenericDAO {

	public List<TipoMaterial> listarTiposMateriales() {
		// TODO Auto-generated method stub
		List<TipoMaterial> l = getSession().createCriteria(TipoMaterial.class).list();
		getSession().flush();
		getSession().close();
		return l;
	}
	
	public List<TipoMaterial> filtrarTiposPorClases(String modelo, String atributo,String valor) {
		// TODO Auto-generated method stub
		List<TipoMaterial> l = getSession().createQuery("FROM " + modelo +" WHERE "+atributo+" = "+"'"+valor+"'").list();
		getSession().flush();
		getSession().close();
		return l;
	}
}
