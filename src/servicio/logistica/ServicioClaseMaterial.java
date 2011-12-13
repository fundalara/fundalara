package servicio.logistica;

import java.util.List;

import org.python.modules.math;

import dao.logistica.ClaseMaterialDAO;
import modelo.ClaseMantenimiento;
import modelo.ClaseMaterial;

public class ServicioClaseMaterial implements IServicioClaseMaterial {

	ClaseMaterialDAO claseMaterialDAO;
	
	@Override
	public void eliminar(ClaseMaterial cm) {
		// TODO Auto-generated method stub
		claseMaterialDAO.eliminar(cm);
	}

	@Override
	public void agregar(ClaseMaterial cm) {
		// TODO Auto-generated method stub
		claseMaterialDAO.guardar(cm);
	}

	@Override
	public void actualizar(ClaseMaterial cm) {
		// TODO Auto-generated method stub
		claseMaterialDAO.actualizar(cm);
	}

	@Override
	public List<ClaseMaterial> listarClasesMateriales() {
		// TODO Auto-generated method stub		
		return claseMaterialDAO.listarClasesMateriales();
	}

	public ClaseMaterialDAO getClaseMaterialDAO() {
		return claseMaterialDAO;
	}

	public void setClaseMaterialDAO(ClaseMaterialDAO claseMaterialDAO) {
		this.claseMaterialDAO = claseMaterialDAO;
	}
	
	public String generarCodigo(){
		return "csm-" + Double.toString(claseMaterialDAO.contarCodigos(new ClaseMaterial())+Math.ceil(Math.random()*100));
	}
}
