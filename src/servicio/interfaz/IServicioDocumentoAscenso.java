package servicio.interfaz;

import java.util.List;

import modelo.DocumentoAscenso;

public interface IServicioDocumentoAscenso {
	
	public abstract void eliminar(DocumentoAscenso c);

	public abstract void agregar(DocumentoAscenso c);

	public abstract void actualizar(DocumentoAscenso c);

	public abstract List<DocumentoAscenso> listar();
}
