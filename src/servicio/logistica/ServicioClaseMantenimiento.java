package servicio.logistica;

import java.util.List;

import dao.logistica.ClaseMantenimientoDAO;
import modelo.ClaseMantenimiento;

public class ServicioClaseMantenimiento implements IServicioClaseMantenimiento {

	ClaseMantenimientoDAO claseMantenimientoDAO; 
	
	
	public ClaseMantenimientoDAO getClaseMantenimientoDAO() {
		return claseMantenimientoDAO;
	}

	public void setClaseMantenimientoDAO(ClaseMantenimientoDAO claseMantenimientoDAO) {
		this.claseMantenimientoDAO = claseMantenimientoDAO;
	}

	@Override
	public void eliminar(ClaseMantenimiento c) {
		this.claseMantenimientoDAO.eliminar(c);
	}

	@Override
	public void guardar(ClaseMantenimiento c) {
		this.claseMantenimientoDAO.guardar(c);
	}

	@Override
	public void actualizar(ClaseMantenimiento c) {
		claseMantenimientoDAO.guardar(c);
	}

	@Override
	public void buscar(ClaseMantenimiento c) {
		// TODO Auto-generated method stub
		
	}
	
	public List<ClaseMantenimiento> listar(){
		return claseMantenimientoDAO.listar();
	}
	
	public String generarCodigo(){
		return "cm-" + Integer.toString(claseMantenimientoDAO.contarCodigos(new ClaseMantenimiento())+1);
	}

}
