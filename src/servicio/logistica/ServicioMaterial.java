package servicio.logistica;

import java.util.List;

import modelo.ClaseMaterial;
import modelo.Material;
import dao.logistica.MaterialDAO;

public class ServicioMaterial implements IServicioMaterial {

	MaterialDAO materialDAO;
	
	@Override
	public void eliminar(Material m) {
		materialDAO.eliminar(m);
	}

	@Override
	public void agregar(Material m) {
		materialDAO.guardar(m);
	}

	@Override
	public void actualizar(Material m) {
		materialDAO.actualizar(m);
	}

	public MaterialDAO getMaterialDAO() {
		return materialDAO;
	}

	public void setMaterialDAO(MaterialDAO materialDAO) {
		this.materialDAO = materialDAO;
	}
	
	
	@Override
	public List<Material> listarMateriales() {
		// TODO Auto-generated method stub		
		return materialDAO.listarMateriales();
	}
	
	public String generarCodigo(){
		Integer nuevoCodigo = materialDAO.contarCodigos(new Material()) + 1;
		return nuevoCodigo.toString();
	}

}
