package servicio.implementacion;

import java.util.List;

import servicio.interfaz.IServicioInstitucion;

import dao.general.DaoInstitucion;
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
	@Override
    public Institucion buscar (String id){
    	return daoInstitucion.buscar(id);
    }
	
}
