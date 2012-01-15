package servicio.implementacion;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import dao.general.DaoConstante;
import dao.general.DaoConstanteCategoria;

import modelo.Categoria;
import modelo.Constante;
import modelo.ConstanteCategoria;
import modelo.Divisa;
import servicio.interfaz.IServicioConstante;

public class ServicioConstante implements IServicioConstante {
	
	DaoConstante daoConstante;
	DaoConstanteCategoria daoConstanteCategoria;
	
	public DaoConstante getDaoConstante() {
		return daoConstante;
	}

	public void setDaoConstante(DaoConstante daoConstante) {
		this.daoConstante = daoConstante;
	}
	
	

	public DaoConstanteCategoria getDaoConstanteCategoria() {
		return daoConstanteCategoria;
	}

	public void setDaoConstanteCategoria(DaoConstanteCategoria daoConstanteCategoria) {
		this.daoConstanteCategoria = daoConstanteCategoria;
	}

	@Override
	public void eliminar(Constante c) {
		c.setEstatus('E');
		daoConstante.eliminar(c);

	}
	
	

	@Override
	public void agregar(Constante c) {
		
		if (c.getCodigoConstante() == 0){
			int codConstante = daoConstante.listar(Constante.class).size()+1;
			c.setCodigoConstante(codConstante);
			c.setEstatus('A');	
			daoConstante.guardar(c);
			daoConstante.getSession().close();
		   
		
		}
		

	}

	/*@Override
	public void actualizar(Constante c) {
		// TODO Auto-generated method stub

	}*/

	/*@Override
	public Constante buscarPorCodigo(Constante c) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<Constante> listar() {
		// TODO Auto-generated method stub
		return daoConstante.listar(Constante.class);
	}

	@Override
	public List<Constante> listarActivos() {
		// TODO Auto-generated method stub
		return daoConstante.listarActivos();
	}
	
	@Override
	public List<Constante> listarConstantesActivos() {
		// TODO Auto-generated method stub
		return daoConstante.listarConstantesActivos();
	}

}