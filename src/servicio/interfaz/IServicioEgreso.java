package servicio.interfaz;

import java.util.List;

import modelo.Egreso ;

public interface IServicioEgreso {
	
	public abstract void eliminar(Egreso  c);
	
	public abstract void agregar(Egreso   c);
		
	public abstract void actualizar(Egreso  c);	
	
	public abstract List listar();

}
