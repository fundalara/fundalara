package servicio.logistica;

import java.util.List;

import modelo.Instalacion;
import modelo.TipoInstalacion;

public interface IServicioInstalacion {

	public abstract void eliminar(Instalacion instalacion);

	public abstract void guardar(Instalacion instalacion);

	public abstract void actualizar(Instalacion instalacion);

	public abstract Instalacion buscar(String instalacion);

	public List<Instalacion> listarInstalacion();

}
