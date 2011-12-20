package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioMaterial;

import modelo.Material;
import dao.general.DaoMaterial;

public class ServicioMaterial implements IServicioMaterial {

	DaoMaterial daoMaterial;
	
	public DaoMaterial getDaoMaterial() {
		return daoMaterial;
	}

	public void setDaoMaterial(DaoMaterial daoMaterial) {
		this.daoMaterial = daoMaterial;
	}

	@Override
	public void eliminar(Material m) {
		daoMaterial.eliminar(m);
	}

	@Override
	public void agregar(Material m) {
		daoMaterial.guardar(m);
	}

	@Override
	public void actualizar(Material m) {
		daoMaterial.actualizar(m);
	}
	
	
	@Override
	public List<Material> listarMateriales() {
		return daoMaterial.listarMateriales();
	}

//	@Override
//	public Material buscarPorCodigo(int cod) {
//		return daoMaterial.buscarPorCodigo(cod);
//	}
	
	
	
//	public String generarCodigo(){
//		Integer nuevoCodigo = daoMaterial.contarCodigos(new Material()) + 1;
//		return nuevoCodigo.toString();
//	}

}
