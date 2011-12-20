package servicio.interfaz;

import java.util.List;

import modelo.Nomina;

public interface IServicioNomina {

	public abstract void eliminar(Nomina c);
	
	public abstract void agregar(Nomina c);
		
	public abstract void actualizar(Nomina c);	
	
	public abstract List listar();

}
