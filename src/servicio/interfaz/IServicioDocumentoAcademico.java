package servicio.interfaz;

import java.util.List;

import modelo.DatoAcademico;
import modelo.DocumentoAcademico;
import modelo.DocumentoEntregado;

/**
 * Interfaz que define el contrato de los servicios para el acceso y manejo de
 * los documentos academicos de los jugadores
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 30/12/2011
 * 
 */
public interface IServicioDocumentoAcademico {
	
	public abstract void eliminar(DocumentoAcademico c);

	public abstract void agregar(DocumentoAcademico c);

	public abstract void actualizar(DocumentoAcademico c);

	public abstract List<DocumentoAcademico> listar();
	
	public abstract void guardar(List<DocumentoEntregado> documentos,
			DatoAcademico datoAcademico);
	
}
