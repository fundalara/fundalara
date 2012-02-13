package servicio.interfaz;

import java.util.List;

import modelo.DatoBasico;
import modelo.Instalacion;
import modelo.InstalacionUtilizada;

public interface IServicioInstalacion {

	public abstract void eliminar(Instalacion instalacion);

	public abstract void agregar(Instalacion instalacion);

	public abstract void actualizar(Instalacion instalacion);

	public abstract Instalacion buscar(String instalacion);

	public List<Instalacion> listar();

	public List<Instalacion> listarActivos();

	public List<Instalacion> listarInstalacionesDisponibles(DatoBasico tipoInstalacion, List<InstalacionUtilizada> instalacionUtilizada);

	public abstract List<Instalacion> buscar(DatoBasico tipo);
}
