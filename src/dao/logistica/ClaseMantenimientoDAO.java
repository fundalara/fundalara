package dao.logistica;

import java.util.List;

import modelo.ClaseMantenimiento;
import dao.general.GenericDAO;

public class ClaseMantenimientoDAO extends GenericDAO {

	public ClaseMantenimiento obtenerClasePorCodigo(String code){
		
		ClaseMantenimiento cm = null;
		
		try{
			cm = (ClaseMantenimiento) getSession().get(ClaseMantenimiento.class,code);
			return cm;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return cm;
	}  

	@Override
	public void guardar(Object c) {
		super.guardar(c);
		getSession().close();
	}

	@Override
	public void actualizar(Object c) {
		// TODO Auto-generated method stub
		super.actualizar(c);
		getSession().close();
	}

	@Override
	public void eliminar(Object c) {
		// TODO Auto-generated method stub
		super.eliminar(c);
		getSession().close();
	}

	@Override
	public Object buscar(Class clase, String id) {
		// TODO Auto-generated method stub
		return super.buscar(clase, id);
	}

	@Override
	public int contarCodigos(Object o) {
		// TODO Auto-generated method stub
		return super.contarCodigos(o);
	}

	public List<ClaseMantenimiento> listar() {
		 List<ClaseMantenimiento> cms = getSession().createCriteria(ClaseMantenimiento.class).list() ;
		 getSession().flush();
		 getSession().close();
		return cms;
	}
	
	

  
	
}
