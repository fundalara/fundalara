package servicio.interfaz;

import java.util.List;

import modelo.DocumentoAcademico;

public interface IServicioDocumentoAcademico {
	
	public abstract void eliminar(DocumentoAcademico c);

	public abstract void agregar(DocumentoAcademico c);

	public abstract void actualizar(DocumentoAcademico c);

	public abstract List<DocumentoAcademico> listar();
}
