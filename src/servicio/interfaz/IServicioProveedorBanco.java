package servicio.interfaz;

import java.util.List;

import modelo.PersonalTipoNomina;
import modelo.ProveedorBanco ;

public interface IServicioProveedorBanco {
	
	public abstract void eliminar(ProveedorBanco  c);
	
	public abstract void agregar(ProveedorBanco  c);
		
	public abstract void actualizar(ProveedorBanco  c);	
	
	public abstract  List<ProveedorBanco> listar ();

	public abstract List<ProveedorBanco> listarActivos();
	
	public abstract ProveedorBanco buscarPorCodigo (ProveedorBanco  d);


}
