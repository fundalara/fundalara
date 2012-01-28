package servicio.interfaz;

import java.util.List;

import modelo.InstalacionEjecutada;

public interface IServicioInstalacionEjecutada {
	public abstract void eliminar(InstalacionEjecutada instalacion);

	public abstract void guardar(InstalacionEjecutada instalacion);

	public abstract void actualizar(InstalacionEjecutada instalacion);

	public List<InstalacionEjecutada> listarInstalacionEjecutada();
}
