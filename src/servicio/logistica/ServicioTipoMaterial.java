package servicio.logistica;

import java.util.List;

import dao.logistica.TipoMaterialDAO;
import modelo.ClaseMantenimiento;
import modelo.ClaseMaterial;
import modelo.TipoMaterial;

public class ServicioTipoMaterial implements IServicioTipoMaterial {

	TipoMaterialDAO tipoMaterialDAO;
	
	@Override
	public void eliminar(TipoMaterial tm) {
		// TODO Auto-generated method stub
		tipoMaterialDAO.eliminar(tm);
	}

	@Override
	public void agregar(TipoMaterial tm) {
		// TODO Auto-generated method stub
		tipoMaterialDAO.guardar(tm);
	}

	@Override
	public void actualizar(TipoMaterial tm) {
		// TODO Auto-generated method stub
		tipoMaterialDAO.actualizar(tm);
	}

	public TipoMaterialDAO getTipoMaterialDAO() {
		return tipoMaterialDAO;
	}

	public void setTipoMaterialDAO(TipoMaterialDAO tipoMaterialDAO) {
		this.tipoMaterialDAO = tipoMaterialDAO;
	}

	@Override
	public List<TipoMaterial> listarTiposMateriales() {
		// TODO Auto-generated method stub
		return tipoMaterialDAO.listarTiposMateriales();
	}

	@Override
	public List<TipoMaterial> filtrarTiposPorClases(String modelo, String atributo,String valor) {
		// TODO Auto-generated method stub
		return tipoMaterialDAO.filtrarTiposPorClases(modelo, atributo, valor);
	}	
	
	public String generarCodigo(){
		return "csm-" + Double.toString(tipoMaterialDAO.contarCodigos(new TipoMaterial())+Math.ceil(Math.random()*100));
	}

}
