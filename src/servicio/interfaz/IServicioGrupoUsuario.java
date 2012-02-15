package servicio.interfaz;

import java.util.List;

import modelo.GrupoUsuario;

public interface IServicioGrupoUsuario {
	
	public abstract void eliminar(GrupoUsuario a);
	
	public abstract void agregar(GrupoUsuario a);
		
	public abstract void actualizar(GrupoUsuario a);

	public abstract List<GrupoUsuario> listar();

	public abstract List<GrupoUsuario> listarActivos();

	public abstract GrupoUsuario buscarPorCodigo(GrupoUsuario d);

}
