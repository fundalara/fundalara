package servicio.interfaz;

import java.util.List;

import modelo.DocumentoEntregado;
import modelo.DocumentoPersonal;
import modelo.Jugador;

/**
 * Interfaz que define el contrato de los servicios para el acceso y manejo de
 * los documentos personales de los jugadores
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 02/01/2012
 * 
 */
public interface IServicioDocumentoPersonal {
	
	public abstract void eliminar(DocumentoPersonal a);
	
	public abstract void agregar(DocumentoPersonal a);
		
	public abstract void actualizar(DocumentoPersonal a);
	
	public abstract  List<DocumentoPersonal> listar();
	
	public abstract void guardar(List<DocumentoEntregado> documentos, Jugador jugador);
	
	
}
