package servicio.interfaz;

import java.util.List;

import modelo.Usuario;

public interface IServicioUsuario {
	public abstract void guardar(Usuario u);

	public abstract void actualizar(Usuario u);

	public abstract void eliminar(Usuario u);

	public abstract List<Usuario> listar();

}
