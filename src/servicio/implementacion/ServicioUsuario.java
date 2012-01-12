package servicio.implementacion;

import java.util.List;

import dao.general.DaoUsuario;

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
		// TODO Auto-generated method stub
		daoUsuario.guardar(u);

	}

	@Override
	public void actualizar(Usuario u) {
		// TODO Auto-generated method stub
daoUsuario.actualizar(u);
	}

	@Override
	public void eliminar(Usuario u) {
		// TODO Auto-generated method stub
daoUsuario.eliminar(u);
	}

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return daoUsuario.listar( DaoUsuario.class);
	}

}
