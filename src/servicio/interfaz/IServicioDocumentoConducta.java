package servicio.interfaz;

import java.util.List;

import modelo.DocumentoConducta;

public interface IServicioDocumentoConducta {
	
	public abstract void eliminar(DocumentoConducta c);

	public abstract void agregar(DocumentoConducta c);

	public abstract void actualizar(DocumentoConducta c);

	public abstract List<DocumentoConducta> listar();
}
