package servicio.interfaz;

import java.util.List;

import modelo.IngresoDocumentoAcreedor ;

public interface IServicioIngresoDocumentoAcreedor {
	
	public abstract void eliminar(IngresoDocumentoAcreedor  c);
	
	public abstract void agregar(IngresoDocumentoAcreedor  c);
		
	public abstract void actualizar(IngresoDocumentoAcreedor  c);	
	
	public abstract List listar();


}
