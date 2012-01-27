package servicio.interfaz;

import java.util.List;

import modelo.DatoDeportivo;

public interface IServicioDatoDeportivo {
	public abstract void eliminar(DatoDeportivo c);
	
	public abstract void agregar(DatoDeportivo c);
		
	public abstract void actualizar(DatoDeportivo c);	
	
	public abstract List<DatoDeportivo> listar();
}
