package servicio.interfaz;

import java.util.List;

import modelo.DonacionMaterial ;

public interface IServicioDonacionMaterial {
	
	public abstract void eliminar(DonacionMaterial c);
	
	public abstract void agregar(DonacionMaterial  c);
		
	public abstract void actualizar(DonacionMaterial c);	
	
	public abstract List listar();


}
