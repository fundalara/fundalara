package servicio.interfaz;

import java.util.List;

import modelo.TipoIngreso;

public interface IServicioTipoIngreso {

	public abstract void eliminar(TipoIngreso c);
	
	public abstract void agregar(TipoIngreso c);
		
	public abstract void actualizar(TipoIngreso c);	
	
	public abstract  List<TipoIngreso> listar ();
	
	public abstract List<TipoIngreso> listarActivos();
	
	public abstract TipoIngreso buscarPorCodigo (TipoIngreso d);

}
