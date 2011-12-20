package servicio.interfaz;


import java.util.List;

import modelo.AfeccionPersonal;
import modelo.CuentaPagar;

public interface IServicioCuentaPagar {
	
	public abstract void eliminar(CuentaPagar c);
	
	public abstract void agregar(CuentaPagar  c);
		
	public abstract void actualizar(CuentaPagar  c);	
	
	public abstract List listar();

}
