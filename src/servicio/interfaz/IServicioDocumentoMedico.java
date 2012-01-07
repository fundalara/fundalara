package servicio.interfaz;

import java.util.List;

import modelo.DocumentoMedico;

public interface IServicioDocumentoMedico {
	
	public abstract void eliminar(DocumentoMedico c);

	public abstract void agregar(DocumentoMedico c);

	public abstract void actualizar(DocumentoMedico c);

	public abstract List<DocumentoMedico> listar();
}
