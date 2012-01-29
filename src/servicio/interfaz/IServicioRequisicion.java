package servicio.interfaz;

import java.util.List;

import modelo.Requisicion;

public interface IServicioRequisicion {

	public abstract void eliminar(Requisicion r);

	public abstract void agregar(Requisicion r);

	public abstract void actualizar(Requisicion r);

	public abstract List<Requisicion> listar();

}
