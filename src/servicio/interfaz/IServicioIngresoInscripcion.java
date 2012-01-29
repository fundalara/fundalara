package servicio.interfaz;

import java.util.List;

import modelo.DocumentoConducta;
import modelo.IngresoInscripcion;

public interface IServicioIngresoInscripcion {
	
	public abstract void eliminar(DocumentoConducta c);

	public abstract void agregar(DocumentoConducta c);

	public abstract void actualizar(DocumentoConducta c);

	public abstract List<IngresoInscripcion> listar();

}
