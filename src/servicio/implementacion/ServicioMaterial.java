package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioMaterial;

import modelo.DatoBasico;
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
	public List<Material> listar() {
		return daoMaterial.listar(Material.class);
	}

	@Override
	public List<Material> listarActivos() {		
		return daoMaterial.listarActivos(Material.class);
	}
	
	public int generarCodigo(){		
		return daoMaterial.generarCodigo(Material.class);
	}

	@Override
	public List<Material> listarMateriales() {
		// TODO Auto-generated method stub
		return daoMaterial.listarMateriales();
	}

	@Override
	public List<Material> listarMaterialPorTipo(DatoBasico tipoMaterial) {
		// TODO Auto-generated method stub
		return daoMaterial.listarMaterialesPorTipo(tipoMaterial);
	}

	@Override
	public List<Material> buscarDatosPorRelacion(DatoBasico m) {
		// TODO Auto-generated method stub
		return daoMaterial.buscarPorRelacion(m);
	}

}
