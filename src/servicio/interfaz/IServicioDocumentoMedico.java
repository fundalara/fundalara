package servicio.interfaz;

import java.util.List;

import modelo.DatoMedico;
import modelo.DocumentoEntregado;
import modelo.DocumentoMedico;

/**
 * Interfaz que define el contrato de los servicios para el acceso y manejo de
 * los documentos medicos de los jugadores
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 02/01/2012
 * 
 */
public interface IServicioDocumentoMedico {
	
	public abstract void eliminar(DocumentoMedico c);

	public abstract void agregar(DocumentoMedico c);

	public abstract void actualizar(DocumentoMedico c);

	public abstract List<DocumentoMedico> listar();
	
	public abstract void guardar(List<DocumentoEntregado> documentos,
			DatoMedico datoMedico);

}
