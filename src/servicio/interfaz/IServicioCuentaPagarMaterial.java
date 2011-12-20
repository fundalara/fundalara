package servicio.interfaz;

import java.util.List;

import modelo.CuentaPagarMaterial;

public interface IServicioCuentaPagarMaterial {
	
	public abstract void eliminar(CuentaPagarMaterial c);
	
	public abstract void agregar(CuentaPagarMaterial c);
		
	public abstract void actualizar(CuentaPagarMaterial c);	
	
	public abstract List listar();

}
