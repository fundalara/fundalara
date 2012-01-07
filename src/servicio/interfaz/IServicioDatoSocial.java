package servicio.interfaz;

import java.util.List;

import modelo.DatoSocial;

public interface IServicioDatoSocial {
	public abstract void eliminar(DatoSocial c);
	
	public abstract void agregar(DatoSocial c);
		
	public abstract void actualizar(DatoSocial c);	
	
	public abstract List<DatoSocial> listar();
}
