package servicio.interfaz;

import java.util.List;

import modelo.IngresoDocumentoAcreedor;
import modelo.IngresoFormaPago ;

public interface IServicioIngresoFormaPago {
	
	public abstract void eliminar( IngresoFormaPago  c);
	
	public abstract void agregar(IngresoFormaPago  c);
		
	public abstract void actualizar(IngresoFormaPago  c);	
	
	public abstract  List<IngresoFormaPago> listar ();
	
	public abstract List<IngresoFormaPago> listarActivos();
	
	public abstract  IngresoFormaPago buscarPorCodigo (IngresoFormaPago d);



}
