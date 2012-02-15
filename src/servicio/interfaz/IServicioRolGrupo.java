package servicio.interfaz;

import java.util.List;

import modelo.RolGrupo;

public interface IServicioRolGrupo {
	
	public abstract void guardar(RolGrupo u);

	public abstract void actualizar(RolGrupo u);

	public abstract void eliminar(RolGrupo u);

	public abstract List<RolGrupo> listar();
	
	public abstract List<RolGrupo> listarActivos();

}
