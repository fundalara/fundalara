package servicio.interfaz;

import java.util.List;

import modelo.EscalaMedicion;

public interface IServicioEscalaMedicion {

	public abstract void guardar(EscalaMedicion em);

	public abstract void actualizar(EscalaMedicion em);

	public abstract void eliminar(EscalaMedicion em);

	public abstract List<EscalaMedicion> listar();

}
