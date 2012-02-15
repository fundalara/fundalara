package servicio.implementacion;

import java.util.List;

import dao.general.DaoGrupoUsuario;
import modelo.Grupo;
import modelo.GrupoUsuario;
import servicio.interfaz.IServicioGrupoUsuario;

public class ServicioGrupoUsuario implements IServicioGrupoUsuario {

	DaoGrupoUsuario daoGrupoUsuario;
	
	@Override
	public void eliminar(GrupoUsuario a) {
		daoGrupoUsuario.eliminar(a);
	}

	@Override
	public void agregar(GrupoUsuario a) {
		daoGrupoUsuario.guardar(a);
	}

	@Override
	public void actualizar(GrupoUsuario a) {
		daoGrupoUsuario.actualizar(a);
	}

	public DaoGrupoUsuario getDaoGrupoUsuario() {
		return daoGrupoUsuario;
	}

	public void setDaoGrupoUsuario(DaoGrupoUsuario daoGrupoUsuario) {
		this.daoGrupoUsuario = daoGrupoUsuario;
	}
	
	@Override
	public List<GrupoUsuario> listar() {
		return daoGrupoUsuario.listar(GrupoUsuario.class);
	}

	@Override
	public List<GrupoUsuario> listarActivos() {
		return daoGrupoUsuario.listarActivos(GrupoUsuario.class);
	}

	@Override
	public GrupoUsuario buscarPorCodigo(GrupoUsuario d) {
		return null;
	}
	
	public List<GrupoUsuario> buscarUsuarioPorGrupo(Grupo grupo){
		return daoGrupoUsuario.buscarUsuariosPorGrupo(grupo);
	}

}
