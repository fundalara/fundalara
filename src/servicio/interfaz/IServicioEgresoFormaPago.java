package servicio.interfaz;

import java.util.List;

import modelo.EgresoFormaPago;

public interface IServicioEgresoFormaPago {
	
	public abstract void eliminar(EgresoFormaPago c);
	
	public abstract void agregar(EgresoFormaPago c);
		
	public abstract void actualizar(EgresoFormaPago c);	
	
	public abstract List listar();


}
