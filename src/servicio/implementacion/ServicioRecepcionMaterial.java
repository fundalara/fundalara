package servicio.implementacion;

import java.util.List;

import dao.general.DaoRecepcionMaterial;
import modelo.RecepcionMaterial;
import servicio.interfaz.IServicioRecepcionMaterial;

public class ServicioRecepcionMaterial implements IServicioRecepcionMaterial {

	DaoRecepcionMaterial daoRecepcionMaterial;
	
	@Override
	public void eliminar(RecepcionMaterial rm) {
		// TODO Auto-generated method stub
		daoRecepcionMaterial.eliminar(rm);
	}

	@Override
	public void agregar(RecepcionMaterial rm) {
		// TODO Auto-generated method stub
		daoRecepcionMaterial.guardar(rm);
	}

	@Override
	public void actualizar(RecepcionMaterial rm) {
		// TODO Auto-generated method stub
		daoRecepcionMaterial.actualizar(rm);
	}
	
	@Override
	public List<RecepcionMaterial> listarMateriales() {
		// TODO Auto-generated method stub
		return daoRecepcionMaterial.listarMateriales();
	}

	@Override
	public String generarCodigo() {
		// TODO Auto-generated method stub
		return null;
	}

	public DaoRecepcionMaterial getDaoRecepcionMaterial() {
		return daoRecepcionMaterial;
	}

	public void setDaoRecepcionMaterial(DaoRecepcionMaterial daoRecepcionMaterial) {
		this.daoRecepcionMaterial = daoRecepcionMaterial;
	}

}
