package servicio.interfaz;

import java.util.List;

import modelo.Ingreso;

public interface IServicioIngreso {

	public abstract void eliminar(Ingreso c);
	
	public abstract void agregar(Ingreso c);
		
	public abstract void actualizar(Ingreso c);	
	
	public abstract List listar();

}
