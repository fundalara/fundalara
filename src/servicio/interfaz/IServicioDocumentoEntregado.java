package servicio.interfaz;

import java.util.List;

import modelo.DocumentoEntregado;

public interface IServicioDocumentoEntregado {
	
	public abstract void eliminar(DocumentoEntregado c);

	public abstract void agregar(DocumentoEntregado c);

	public abstract void actualizar(DocumentoEntregado c);

	public abstract List<DocumentoEntregado> listar();
}
