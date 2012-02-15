package servicio.interfaz;

import java.util.List;

import modelo.DocumentoConducta;
import modelo.IngresoInscripcion;

public interface IServicioIngresoInscripcion {
	
	public abstract void eliminar(IngresoInscripcion c);

	public abstract void agregar(IngresoInscripcion c);

	public abstract void actualizar(IngresoInscripcion c);

	public abstract List<IngresoInscripcion> listar();

}
