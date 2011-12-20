package servicio.implementacion;

import java.util.List;

import dao.general.DaoSeguridadFuncional;

import modelo.SeguridadFuncional;
import servicio.interfaz.IServicioSeguridadFuncional;

public class ServicioSeguridadFuncional implements IServicioSeguridadFuncional {
	DaoSeguridadFuncional daoSeguridadFuncional;
	
	
    public DaoSeguridadFuncional getDaoSeguridadFuncional() {
		return daoSeguridadFuncional;
	}

	public void setDaoSeguridadFuncional(DaoSeguridadFuncional daoSeguridadFuncional) {
		this.daoSeguridadFuncional = daoSeguridadFuncional;
	}

	
	@Override
	public void eliminar(SeguridadFuncional s) {
		// TODO Auto-generated method stub
		daoSeguridadFuncional.eliminar(s);

	}

	@Override
	public void agregar(SeguridadFuncional s) {
		// TODO Auto-generated method stub
		daoSeguridadFuncional.guardar(s);

	}

	@Override
	public List<SeguridadFuncional> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SeguridadFuncional> listarActivos() {
		// TODO Auto-generated method stub
		return null;
	}

}
