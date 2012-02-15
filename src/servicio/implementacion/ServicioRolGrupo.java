package servicio.implementacion;

import java.util.List;

import dao.general.DaoRolGrupo;
import modelo.Grupo;
import modelo.GrupoUsuario;
import modelo.Rol;
import modelo.RolGrupo;
import servicio.interfaz.IServicioRolGrupo;

public class ServicioRolGrupo implements IServicioRolGrupo {

	DaoRolGrupo daoRolGrupo;

	public DaoRolGrupo getDaoRolGrupo() {
		return daoRolGrupo;
	}

	public void setDaoRolGrupo(DaoRolGrupo daoRolGrupo) {
		daoRolGrupo = daoRolGrupo;
	}

	@Override
	public void guardar(RolGrupo u) {
		daoRolGrupo.guardar(u);
	}

	@Override
	public void actualizar(RolGrupo u) {
		daoRolGrupo.actualizar(u);
	}

	@Override
	public void eliminar(RolGrupo u) {
		daoRolGrupo.eliminar(u);
	}

	@Override
	public List<RolGrupo> listar() {
		return daoRolGrupo.listar(RolGrupo.class);
	}

	@Override
	public List<RolGrupo> listarActivos() {
		return daoRolGrupo.listarActivos(RolGrupo.class);
	}
	
	public List<RolGrupo> buscarRolesPorGrupo(Grupo grupo){
		return daoRolGrupo.buscarRolesPorGrupo(grupo);
	}
	
	public RolGrupo buscarRolExistente(Rol rol, Grupo grupo){
		return daoRolGrupo.buscarRolExistente(rol, grupo);
	}
}
