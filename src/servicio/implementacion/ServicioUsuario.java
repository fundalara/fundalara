package servicio.implementacion;

import java.util.List;

import dao.general.DaoUsuario;

import modelo.Grupo;
import modelo.Usuario;
import servicio.interfaz.IServicioUsuario;

public class ServicioUsuario implements IServicioUsuario {
	DaoUsuario daoUsuario;

	public DaoUsuario getDaoUsuario() {
		return daoUsuario;
	}

	public void setDaoUsuario(DaoUsuario daoUsuario) {
		this.daoUsuario = daoUsuario;
	}

	@Override
	public void guardar(Usuario u) {
		daoUsuario.guardar(u);
	}

	@Override
	public void actualizar(Usuario u) {
		daoUsuario.actualizar(u);
	}

	@Override
	public void eliminar(Usuario u) {
		daoUsuario.eliminar(u);
	}

	@Override
	public List<Usuario> listar() {
		return daoUsuario.listar(DaoUsuario.class);
	}

	@Override
	public Usuario buscarPorNombre(String nombre) {
		return daoUsuario.buscarPorNombre(nombre);
	}

	@Override
	public List<Usuario> listarActivos() {
		return daoUsuario.listarActivos(Usuario.class);
	}

	@Override
	public List<Usuario> buscarUsuariosSinAsignar(Grupo grupo) {
		return daoUsuario.buscarUsuariosSinAsignar(grupo);
	}
}