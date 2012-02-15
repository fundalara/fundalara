package servicio.implementacion;

import java.util.List;

import dao.general.DaoRol;
import modelo.Grupo;
import modelo.Rol;
import modelo.Usuario;
import servicio.interfaz.IServicioRol;

public class ServicioRol implements IServicioRol {

	DaoRol daoRol;
	
	@Override
	public void eliminar(Rol a) {
		daoRol.eliminar(a);
	}

	@Override
	public void actualizar(Rol a) {
		daoRol.actualizar(a);
	}

	public DaoRol getDaoRol() {
		return daoRol;
	}

	public void setDaoRol(DaoRol daoRol) {
		this.daoRol = daoRol;
	}

	@Override
	public void guardar(Rol u) {
		this.daoRol.guardar(u);
	}

	@Override
	public List<Rol> listar() {
		return this.daoRol.listar(Rol.class);
	}

	@Override
	public List<Rol> listarActivos() {
		return this.daoRol.listarActivos(Rol.class);
	}
	
	public List<Rol> buscarRolesSinAsignar(Grupo grupo) {
		return daoRol.buscarRolesSinAsignar(grupo);
	}
	
//	public List<Rol> buscarRolesSinAsignar() {
//		return daoRol.buscarRolesSinAsignar();
//	}
	
	public List<Rol> listarPorPadre(Rol rol) {
		return daoRol.listarPorPadre(rol);
	}
	
	public Rol buscarPorCodigo(int rol){
		return daoRol.buscarPorCodigo(rol);
	}
	
}
