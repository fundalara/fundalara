package servicio.implementacion;

import java.util.Iterator;
import java.util.List;

import dao.general.DaoConstanteCategoria;

import modelo.ConstanteCategoria;
import servicio.interfaz.IServicioConstanteCategoria;

public class ServicioConstanteCategoria implements IServicioConstanteCategoria {
	
	DaoConstanteCategoria daoConstanteCategoria;
	
	public DaoConstanteCategoria getDaoConstanteCategoria() {
		return daoConstanteCategoria;
	}

	public void setDaoConstanteCategoria(DaoConstanteCategoria daoConstanteCategoria) {
		this.daoConstanteCategoria = daoConstanteCategoria;
	}

	@Override
	public void eliminar(ConstanteCategoria c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agregar(List<ConstanteCategoria> l) {
		for (Iterator i= l.iterator(); i.hasNext();){
	    	ConstanteCategoria id = (ConstanteCategoria) i.next();
	    	int codConstanteCategoria = daoConstanteCategoria.listar(ConstanteCategoria.class).size()+1;
	    	id.setCodigoConstanteCategoria(codConstanteCategoria);
	    	System.out.println(id.getCodigoConstanteCategoria());
	    	System.out.println(id.getConstante().getCodigoConstante());
		    daoConstanteCategoria.guardar(id);		
	    }

	}

	@Override
	public List<ConstanteCategoria> listar() {
		// TODO Auto-generated method stub
		return daoConstanteCategoria.listar(ConstanteCategoria.class);
	}

	//@Override
	//public List<ConstanteCategoria> listarActivos() {
		// TODO Auto-generated method stub
		//return daoConstanteCategoria.listarActivos();
	//}

}