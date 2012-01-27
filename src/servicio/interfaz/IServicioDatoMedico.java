package servicio.interfaz;

import java.util.List;

import modelo.DatoMedico;

/**
 * Interfaz  que define el contrato de los servicios  para el acceso y manejo de los datos  medicos 
 * @author Robert A
 * @author German L
 * @version 0.1 30/12/2011
 *
 */
public interface IServicioDatoMedico {
	
	public abstract void eliminar(DatoMedico c);
	
	public abstract void agregar(DatoMedico c);
		
	public abstract void actualizar(DatoMedico c);	
	
	public abstract List<DatoMedico> listar();
	
	public abstract int obtenerUltimoId();
}
