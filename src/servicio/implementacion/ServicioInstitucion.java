package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioInstitucion;

import dao.general.DaoInstitucion;
import modelo.DatoBasico;
import modelo.Institucion;

public class ServicioInstitucion implements IServicioInstitucion {

	DaoInstitucion daoInstitucion;
	
	public DaoInstitucion getDaoInstitucion() {
		return daoInstitucion;
	}

	public void setDaoInstitucion(DaoInstitucion daoInstitucion) {
		this.daoInstitucion = daoInstitucion;
	}

	
	
	@Override
	public void eliminar(Institucion c) {
		 daoInstitucion.eliminar(c);
	}

	@Override
	public void agregar(Institucion c) {
		 daoInstitucion.guardar(c);
	}

	@Override
	public void actualizar(Institucion c) {
		 daoInstitucion.actualizar(c);
	}

	@Override
	public List<Institucion> listar() {
		return  daoInstitucion.listar( Institucion.class);
	}

	
    public Institucion buscar (int id){
    	return daoInstitucion.buscar(id);
    }
	
	@Override
	public List<Institucion> buscarInstitucionTipo(DatoBasico datoBasico) {
		return daoInstitucion.buscarInstitucionTipo(datoBasico);
	}
	
	
	public Institucion buscarpornombre (String nombre){
    	return daoInstitucion.buscarpornombre(nombre);
    }

	@Override
	public Institucion buscar(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
